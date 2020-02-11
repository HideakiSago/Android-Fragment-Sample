package jp.hideakisago.androidfragmentsample.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import jp.hideakisago.androidfragmentsample.Feature1Activity
import jp.hideakisago.androidfragmentsample.R
import kotlinx.android.synthetic.main.fragment_gallery.*
import timber.log.Timber

class GalleryFragment : Fragment() {
    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        galleryViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_gallery.setOnClickListener {
            startActivityForResult(Intent(requireContext(), Feature1Activity::class.java), 1)
        }
    }

    // onActivityResult on Nested Fragment
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Timber.d("onActivityResult action:${data?.action} data:${data?.data} requestCode:$requestCode resultCode:$resultCode")
    }
}
