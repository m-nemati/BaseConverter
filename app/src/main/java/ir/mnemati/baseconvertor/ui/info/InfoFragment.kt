package ir.mnemati.baseconvertor.ui.info

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ir.mnemati.baseconvertor.R


class InfoFragment : Fragment() {

    private lateinit var infoViewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoViewModel =
            ViewModelProvider(this).get(InfoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_info, container, false)

        val btn_email:Button = root.findViewById(R.id.btn_email)

        btn_email.setOnClickListener {

            val email_intent = Intent(Intent.ACTION_SEND)
            email_intent.type = "*/*"
            email_intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("m.nemati2002@gmail.com"))
            email_intent.putExtra(Intent.EXTRA_SUBJECT,"عنوان")
            email_intent.putExtra(Intent.EXTRA_TEXT, "متن ایمیل")
            //if(email_intent.resolveActivity(root.packageManager) != null){ startActivity(email_intent)}
            startActivity(email_intent)
        }
        return root
    }
}