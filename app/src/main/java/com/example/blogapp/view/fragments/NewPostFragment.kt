package com.example.blogapp.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.blogapp.R
import com.example.blogapp.databinding.LayoutFragmentNewPostBinding
import com.example.blogapp.datasource.ResponsePost
import com.example.blogapp.model.ModelPost
import com.example.blogapp.util.Constants.Companion.HAS_TEXT_DESCRIPTION
import com.example.blogapp.util.Constants.Companion.HAS_TEXT_TITLE
import com.example.blogapp.util.DateActual
import com.example.blogapp.viewmodel.ViewModelRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Create by Julio
 */
class NewPostFragment : Fragment() {
    private lateinit var viewBinding: LayoutFragmentNewPostBinding
    private var viewModelRepository: ViewModelRepository? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = LayoutFragmentNewPostBinding.inflate(layoutInflater)

        configureItems()
        viewModelRepository = ViewModelRepository()
        /*setOnBackPressedDispactcher()*/
        return viewBinding.root
    }

    /* private fun setOnBackPressedDispactcher() {
         requireActivity().onBackPressedDispatcher.onBackPressed(
             requireActivity(),
             object : OnBackPressedCallback(true){
                 override fun handleOnBackPressed() {
                     parentFragmentManager.beginTransaction().remove(this@NewPostFragment).commit()
                 }
             }
         )
     }*/

    private fun configureItems() {
        viewBinding.btnAddNewPost.setOnClickListener {
            saveMyPost()
        }

        viewBinding.editNewPost.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                checkEditTitleIsEmpty(text.toString())
            }

            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        viewBinding.editTitleNewPost.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                checkEditDescEmpty(text.toString())
            }

            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun saveMyPost() {
        if (HAS_TEXT_TITLE && HAS_TEXT_DESCRIPTION) {
            val dataNow = DateActual()
            val modelPost = ModelPost(
                viewBinding.editTitleNewPost.text.toString(),
                dataNow.getDateNow(),
                viewBinding.editNewPost.text.toString(),
                ""
            )
            setProgress()
            viewModelRepository?.postNewBlog(modelPost)?.enqueue(object :
                Callback<ResponsePost> {
                override fun onResponse(
                    call: Call<ResponsePost>,
                    response: Response<ResponsePost>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Post cadastrado com sucesso!", Toast.LENGTH_SHORT)
                            .show()
                        setTexts()
                        hideProgress()
                    }
                }

                override fun onFailure(call: Call<ResponsePost>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    private fun setTexts() {
        viewBinding.editNewPost.setText("")
        viewBinding.editTitleNewPost.setText("")
    }

    private fun setBackgroundBottom() {
        if (HAS_TEXT_TITLE && HAS_TEXT_DESCRIPTION) viewBinding.btnAddNewPost.setBackgroundResource(
            R.drawable.bord_bottom
        )
        else viewBinding.btnAddNewPost.setBackgroundResource(R.drawable.bord_bottom_inactive)
    }

    private fun checkEditTitleIsEmpty(text: String) {
        if (context?.getString(R.string.empty) != text) {
            HAS_TEXT_TITLE = true
            setBackgroundBottom()
        } else {
            HAS_TEXT_TITLE = false
            Toast.makeText(
                context,
                context?.getString(R.string.empty_description),
                Toast.LENGTH_SHORT
            ).show()
            setBackgroundBottom()
        }
    }

    private fun checkEditDescEmpty(text: String) {
        if (context?.getString(R.string.empty) != text) {
            HAS_TEXT_DESCRIPTION = true
            setBackgroundBottom()
        } else {
            HAS_TEXT_DESCRIPTION = false
            Toast.makeText(
                context,
                context?.getString(R.string.empty_description),
                Toast.LENGTH_SHORT
            ).show()
            setBackgroundBottom()
        }
    }

    fun setProgress() {
        viewBinding.progress.visibility = View.VISIBLE
    }

    fun hideProgress() {
        viewBinding.progress.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewPostFragment()
    }
}