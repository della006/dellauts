package com.example.daftarteman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.daftarteman.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private var auth:FirebaseAuth? = null
    private val RC_SIGN_IN  = 1
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logout.setOnClickListener(this)
        binding.save.setOnClickListener(this)
        binding.showData.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()
    }

    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }

    private fun getJkel(): String {
        var gender = ""
        if (binding.laki.isChecked) {
            gender = "Laki Laki"
        } else if (binding.perempuan.isChecked) {
            gender = "Perempuan"
        }
        return gender
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.save -> {
                val getUserID = auth!!.currentUser!!.uid
                val database = FirebaseDatabase.getInstance()
                val getNama: String = binding.nama.getText().toString()
                val getAlamat: String = binding.alamat.getText().toString()
                val getNoHp: String = binding.noHp.getText().toString()
                val getJkel = getJkel()

                val getReference: DatabaseReference
                getReference = database.reference
                if (isEmpty(getNama) || isEmpty(getAlamat) || isEmpty(getNoHp) || isEmpty(getJkel)) {
                    Toast.makeText(this@MainActivity, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
                } else {
                    getReference.child("Admin").child(getUserID).child( "DataTeman").push()
                    .setValue(data_teman(getNama, getAlamat, getNoHp, getJkel))
                        .addOnCompleteListener(this) {
                            binding.nama.setText("")
                            binding.alamat.setText("")
                            binding.noHp.setText("")
                            binding.laki.isChecked = false
                            binding.perempuan.isChecked = false
                            Toast.makeText(this@MainActivity, "Data tersimpan", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            R.id.logout -> {
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(object : OnCompleteListener<Void> {
                        override fun onComplete(pe: Task<Void>) {
                            Toast.makeText(
                                this@MainActivity, "Logout Berhasil",
                                Toast.LENGTH_SHORT
                            ).show()
                            intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    })
            }
            R.id.show_data -> {
                startActivity(Intent( this@MainActivity, MyListData::class.java))
            }
        }
    }
}