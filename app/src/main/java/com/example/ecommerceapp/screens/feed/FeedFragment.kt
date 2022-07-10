//package com.example.ecommerceapp.screens.feed
//
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.core.os.bundleOf
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.ecommerceapp.R
//import com.example.ecommerceapp.databinding.FragmentFeedBinding
//import com.example.ecommerceapp.screens.feed.model.ProductItem
//import com.example.ecommerceapp.screens.ui.main.TestFragment
//import org.json.JSONException
//import org.json.JSONObject
//import java.io.IOException
//import java.nio.charset.Charset
//
//class FeedFragment : Fragment(), IFeedRVAdapter  {
//
//    private var itemList: ArrayList<ProductItem> = ArrayList()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        val binding = DataBindingUtil.inflate<FragmentFeedBinding>(
//            inflater, R.layout.fragment_feed, container, false)
//
//        binding.feedRv.layoutManager = LinearLayoutManager(context)
//        addItemsFromJSON()
//        val adapter = context?.let { FeedRVAdapter(it, this, itemList) }
//        binding.feedRv.adapter = adapter
//
//        return binding.root
//    }
//
//    override fun onItemClicked(item: ProductItem) {
//        Toast.makeText(context,"Item added to cart", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun addItemsFromJSON() {
//        try {
//            val obj = JSONObject(loadJSONFromAsset())
//            val productArray = obj.getJSONArray("products")
//            for (i in 0 until productArray.length()) {
//                val userDetail = productArray.getJSONObject(i)
//                val title = userDetail.getString("title")
//                val image = userDetail.getString("image")
//                val price = userDetail.getString("price")
//                val id = userDetail.getString("id")
//                val item = ProductItem(
//                    image = image,
//                    title = title,
//                    price = price.toInt(),
//                    id = id.toInt()
//                )
//                itemList.add(item)
//            }
//            Log.d("dataa", itemList.toString())
//        }
//        catch (e: JSONException) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun loadJSONFromAsset(): String? {
//        val json: String?
//        try {
//            val inputStream = context?.getAssets()?.open("homefeed.txt")
//            val size = inputStream?.available()
//            val buffer = size?.let { ByteArray(it) }
//            val charset: Charset = Charsets.UTF_8
//            inputStream?.read(buffer)
//            inputStream?.close()
//            json = buffer?.let { String(it, charset) }
//        }
//        catch (ex: IOException) {
//            ex.printStackTrace()
//            return ""
//        }
//        return json
//    }
//
//    companion object {
//        fun newInstance() = FeedFragment()
//    }
//
//}
