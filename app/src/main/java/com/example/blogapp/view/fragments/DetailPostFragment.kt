package com.example.blogapp.view.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.blogapp.R
import com.example.blogapp.converter.presentation.DetailsPresentation
import com.example.blogapp.databinding.LayoutFragmentDatailPostBinding
import com.example.blogapp.datasource.ResponsePost
import com.example.blogapp.model.ModelPost
import com.example.blogapp.viewmodel.ViewModelDetail
import com.example.blogapp.viewmodel.ViewModelRepository
import retrofit2.Call
import retrofit2.Response


/**
 * Create by Julio
 */
class DetailPostFragment : Fragment() {

    private lateinit var viewBinding: LayoutFragmentDatailPostBinding
    private var modelPostBlog: ModelPost? = null
    private var viewModelDetails: ViewModelDetail? = null
    private var viewModelRemove: ViewModelRepository? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = LayoutFragmentDatailPostBinding.inflate(layoutInflater)

        viewModelDetails = ViewModelDetail()
        viewModelRemove = ViewModelRepository()

        modelPostBlog?.let { viewModelDetails!!.presentation(it) }
            ?.let { configurePresentation(it) }


        return viewBinding.root
    }

    private fun configurePresentation(detailsPresentation: DetailsPresentation) {
        viewBinding.titleDetails.text = detailsPresentation.title
        viewBinding.textDatePost.text = detailsPresentation.date
        viewBinding.textDescription.text = detailsPresentation.description

        viewBinding.btnRemovePost.setOnClickListener {
            openDialogAlert()
        }
    }

    private fun openDialogAlert() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_alert)

        val dialogButton = dialog.findViewById<View>(R.id.btnRemoveYes) as Button
        dialogButton.setOnClickListener {
            dialog.dismiss()
            removeItemPost()
        }

        dialog.show()
    }

    private fun removeItemPost() {

        modelPostBlog?.let { viewModelRemove?.removePost(it) }?.enqueue(object :
            retrofit2.Callback<ResponsePost?> {
            override fun onResponse(call: Call<ResponsePost?>, response: Response<ResponsePost?>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Post deletado com sucesso", Toast.LENGTH_SHORT)
                        .show()
                    setTextItem()
                    requireActivity().onBackPressed()
                }
            }

            override fun onFailure(call: Call<ResponsePost?>, t: Throwable) {
               t.printStackTrace()
            }
        })

    }

    private fun setTextItem() {
        viewBinding.titleDetails.text = ""
        viewBinding.textDatePost.text = ""
        viewBinding.textDescription.text = ""
    }


    companion object {
        @JvmStatic
        fun newInstance(modelPost: ModelPost) = DetailPostFragment().apply {
            arguments = Bundle().apply {
                modelPostBlog = modelPost
            }
        }
    }
}