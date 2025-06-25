package com.mobile.bce.sideNav

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.app.ActivityCompat.recreate
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.mobile.bce.FormActivity
import com.mobile.bce.PersonActivity
import com.mobile.bce.ProductActivity
import com.mobile.bce.R
import java.util.Locale

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val btnGo = view.findViewById<Button>(R.id.btnRegister)
//        val btnPersonList = view.findViewById<Button>(R.id.btnPersonList)
        val btnDialog = view.findViewById<Button>(R.id.btnDialog)
        val btnEnglish = view.findViewById<Button>(R.id.btnEnglish)
        val btnNepali = view.findViewById<Button>(R.id.btnNepali)
        val btnProduct = view.findViewById<Button>(R.id.btnProduct)
        val btnForm = view.findViewById<Button>(R.id.btnForm)


        btnForm.setOnClickListener {
            val intent = Intent(requireActivity(), FormActivity::class.java)
            startActivity(intent)
        }


        btnDialog.setOnClickListener {
            showDialogBox()
        }

        btnEnglish.setOnClickListener {
            setLanguage("en")
        }

        btnNepali.setOnClickListener {
            setLanguage("ne")
        }

        btnProduct.setOnClickListener {
            val intent = Intent(requireContext(), ProductActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showDialogBox() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit App")
            .setMessage("Do you want to exit?")
            .setPositiveButton("NO") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Yes"){dialog, _ ->
                activity?.finishAffinity()
            }
            .show()
    }

    private fun setLanguage(languageCode: String) {
        val context = requireContext()
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)

        // Save language
        val editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", languageCode)
        editor.apply()

        // Recreate the Activity to apply changes
        requireActivity().recreate()
    }


}