package com.example.motionhack_2023_kel_5

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.motionhack_2023_kel_5.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import android.app.Activity.RESULT_OK
import android.graphics.Bitmap
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    lateinit var auth: FirebaseAuth
    private lateinit var imgUri : Uri

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Nampilin nama sama email
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user!=null) {
            //if (user.photoUrl!=null){
            //    binding.civUser.setImageBitmap(Bitmap)
            //}
            binding.edtName.setText(user.displayName)
            binding.edtEmail.setText(user.email)


        }

        binding.civUser.setOnClickListener {
            goToCamera()
        }

        binding.btnLogout.setOnClickListener {
            btnLogout()
        }

//        binding.btnChangePassword.setOnClickListener {
//            changePassword()
//        }
//
//        binding.btnDeleteAccount.setOnClickListener {
//            deleteAccount()
//        }
    }

//    private fun deleteAccount(){
//        //Hapus Akun
//        auth = FirebaseAuth.getInstance()
//        val user = auth.currentUser
//
//        binding.btnDeleteAccount.setOnClickListener {
//            user?.delete()?.addOnCompleteListener {
//                //Akun sudah dihapus
//                if (it.isSuccessful){
//                    auth.signOut()
//                    activity?.finish()
//                    val intent = Intent(context, LoginActivity::class.java)
//                    startActivity(intent)
//                }
//                Toast.makeText(activity, "Account successfully deleted!", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    //Ganti Password
//    private fun changePassword() {
//        auth = FirebaseAuth.getInstance()
//        val user = auth.currentUser
//
//        binding.cvCurrentPassword.visibility = View.VISIBLE
//
//        binding.btnCancel.setOnClickListener {
//            binding.cvCurrentPassword.visibility
//        }
//        binding.btnConfirm.setOnClickListener btnConfirm@{
//            val pass = binding.edtCurrentPassword.text.toString()
//            if (pass.isEmpty()) {
//                binding.edtCurrentPassword.error = "Password cannot be empty!"
//                binding.edtCurrentPassword.requestFocus()
//                return@btnConfirm
//            }
//            user.let {
//                val userCredential = EmailAuthProvider.getCredential(it?.email!!, pass)
//                it.reauthenticate(userCredential).addOnCompleteListener { task->
//                    when {
//                        task.isSuccessful -> {
//                            binding.cvCurrentPassword.visibility = View.GONE
//                            binding.cvUpdatePassword.visibility = View.VISIBLE
//                        }
//                        task.exception is FirebaseAuthInvalidCredentialsException -> {
//                            binding.edtCurrentPassword.error = "Password wrong!"
//                            binding.edtCurrentPassword.requestFocus()
//                        }
//                        else -> {
//                            Toast.makeText(activity, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }
//            binding.btnNewCancel.setOnClickListener {
//                binding.cvCurrentPassword.visibility = View.GONE
//                binding.cvUpdatePassword.visibility = View.GONE
//            }
//            binding.btnNewChange.setOnClickListener newChangePassword@{
//                val newPass = binding.edtNewPassword.text.toString()
//                val passConfirm = binding.edtConfirmPassword.text.toString()
//
//                if (newPass.isEmpty()) {
//                    binding.edtCurrentPassword.error = "Password cannot be empty!"
//                    binding.edtCurrentPassword.requestFocus()
//                    return@newChangePassword
//                }
//                else if (passConfirm.isEmpty()) {
//                    binding.edtCurrentPassword.error = "Repeat new password!"
//                    binding.edtCurrentPassword.requestFocus()
//                    return@newChangePassword
//                }
//                else if (newPass.length < 6) {
//                    binding.edtCurrentPassword.error = "Password must be more than 6 characters!"
//                    binding.edtCurrentPassword.requestFocus()
//                    return@newChangePassword
//                }
//                else if (passConfirm.length < 6) {
//                    binding.edtCurrentPassword.error = "Password and confirmation password are not the same!"
//                    binding.edtCurrentPassword.requestFocus()
//                    return@newChangePassword
//                }
//                else if (newPass != passConfirm) {
//                    binding.edtCurrentPassword.error = "Password Tidak Sama"
//                    binding.edtCurrentPassword.requestFocus()
//                    return@newChangePassword
//                }
//                user?.let {
//                    user.updatePassword(newPass).addOnCompleteListener {
//                        if (it.isSuccessful) {
//                            Toast.makeText(activity, "Password updated successfully", Toast.LENGTH_SHORT).show()
//                            successLogout()
//                        } else {
//                            Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }
//        }
//    }
    private fun successLogout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()

        Toast.makeText(activity, "Please login again with the new password", Toast.LENGTH_SHORT).show()
    }
    private fun goToCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            activity?.packageManager?.let {
                intent?.resolveActivity(it).also {
                    startActivityForResult(intent, REQ_CAM)
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CAM && resultCode == RESULT_OK) {
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImgToFirebase(imgBitmap)
        }
    }
    private fun uploadImgToFirebase(imgBitmap : Bitmap) {
        val BAOS = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference.child("img_user/${FirebaseAuth.getInstance().currentUser?.email}")
        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, BAOS)

        val img = BAOS.toByteArray()
        ref.putBytes(img)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    ref.downloadUrl.addOnCompleteListener { Task->
                        Task.result.let{ Uri->
                            imgUri = Uri
                            binding.civUser.setImageBitmap(imgBitmap)
                        }
                    }
                }
            }
    }
    private fun btnLogout(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    companion object {
        const val REQ_CAM = 100
    }
}