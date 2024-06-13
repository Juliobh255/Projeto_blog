package com.example.blogapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.blogapp.R
import com.example.blogapp.adapters.AdapterListPost
import com.example.blogapp.converter.presentation.ListPresentation
import com.example.blogapp.databinding.LayoutFragmentListPostBinding
import com.example.blogapp.model.ModelPost
import com.example.blogapp.viewmodel.ViewModelMyList
import com.example.blogapp.viewmodel.ViewModelRepository
import retrofit2.Call
import retrofit2.Response

/**
 * Create by Julio
 */
class ListPostFragment : Fragment() {
    private lateinit var viewBinding: LayoutFragmentListPostBinding
    private var pagerSnapHelper: PagerSnapHelper? = null
    private var viewModelRepository: ViewModelRepository? = null
    private var viewModelMyList: ViewModelMyList? = null
    private var listModel:List<ModelPost>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = LayoutFragmentListPostBinding.inflate(layoutInflater)

        pagerSnapHelper = PagerSnapHelper()
        viewModelRepository = ViewModelRepository()
        viewModelMyList = ViewModelMyList()

        setProgress()
        requestMyList()
        uploadPost()
        return viewBinding.root
    }

    private fun uploadPost() {
       viewBinding.floatingActionButton.setOnClickListener {
           setProgress()
           requestMyList()
       }
    }

    private fun requestMyList() {
        if(listModel != null){
            listModel = null
        }
        val resultCall = viewModelRepository?.getListBlog()
        resultCall?.enqueue(object : retrofit2.Callback<List<ListPresentation>> {
            override fun onResponse(
                call: Call<List<ListPresentation>>,
                response: Response<List<ListPresentation>>
            ) {
                response.body()?.forEachIndexed { index, listPresentation ->
                    val modelPost = ModelPost(
                        listPresentation.title,
                        listPresentation.date,
                        listPresentation.description,
                        listPresentation.id
                    )

                    listModel = listOf(modelPost) + (listModel?.toList() ?: listOf())
                }

                if(listModel!= null){
                    configureAdapterListPost(listModel?: listOf())
                    viewBinding.lottieAnimationList.visibility = View.GONE
                    viewBinding.textTitleAnimation.visibility = View.GONE
                }else{
                    hideProgress()
                    setAnimationEmpty()
                }
            }

            override fun onFailure(call: Call<List<ListPresentation>>, t: Throwable) {
                t.printStackTrace()
                hideProgress()
                setAnimationEmpty()
            }
        })
    }

    private fun setAnimationEmpty() {
       viewBinding.lottieAnimationList.visibility = View.VISIBLE
       viewBinding.textTitleAnimation.visibility = View.VISIBLE
    }

    private fun configureAdapterListPost(listModel: List<ModelPost>) {
        val adapterListPost = AdapterListPost(requireContext(), listModel, object :
            AdapterListPost.OnclickListener {
            override fun onClickItem(modelPosts: ModelPost) {
                openMyPostDetail(modelPosts)
            }

            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }
        })
        viewBinding.recycleListPost?.adapter = adapterListPost
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewBinding.recycleListPost?.layoutManager = layoutManager
        hideProgress()
    }

    private fun openMyPostDetail(modelPost: ModelPost) {
        val fragments = DetailPostFragment.newInstance(modelPost)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        fragments?.let { transaction?.replace(R.id.nav_controller_view, it) }
        transaction?.addToBackStack(null)?.commit()
    }

    fun setProgress(){
        viewBinding.progress.visibility = View.VISIBLE
    }

    fun hideProgress(){
        viewBinding.progress.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListPostFragment()
    }
}