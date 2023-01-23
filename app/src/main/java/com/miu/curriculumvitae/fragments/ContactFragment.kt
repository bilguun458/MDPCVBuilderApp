package com.miu.curriculumvitae.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.miu.curriculumvitae.R
import com.miu.curriculumvitae.WebViewActivity
import com.miu.curriculumvitae.Person
import kotlinx.android.synthetic.main.fragment_contact.view.*

class ContactFragment : Fragment() {
    private lateinit var person: Person

    fun newInstance(person: Person): ContactFragment {
        val args = Bundle()
        val fragment = ContactFragment()
        args.putSerializable("person", person)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_contact, container, false)
        person = arguments?.getSerializable("person") as Person
        view.txt_phone.text = "${person.contact.phone}"
        var phone = view.findViewById<LinearLayout>(R.id.phone)
        phone.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:${person.contact.phone}"));
                startActivity(intent);
            }
        })


        var gmail = view.findViewById<LinearLayout>(R.id.gmail)
        gmail.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val uriText = "mailto:${person.contact.email}" +
                        "?subject=" + Uri.encode("Demo email") +
                        "&body=" + Uri.encode("This is demo email")

                val uri = Uri.parse(uriText)

                val sendIntent = Intent(Intent.ACTION_SENDTO)
                sendIntent.data = uri
                startActivity(Intent.createChooser(sendIntent, "Send email"))
            }
        })

        var facebook = view.findViewById<LinearLayout>(R.id.facebook)
        facebook.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                    var intent =
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("fb://profile/${person.contact.facebook}")
                        );
                    startActivity(intent);
            }
        })
        var twitter = view.findViewById<LinearLayout>(R.id.twitter)
        twitter.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                    var intent =
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("twitter://user?screen_name=${person.contact.twitter}")
                        );
                    startActivity(intent);
            }
        })


        //--- buttons
        view.button_web.setOnClickListener {
            startActivity(Intent(context, WebViewActivity::class.java).putExtra("web", person.webs))
        }
        return view
    }

}
