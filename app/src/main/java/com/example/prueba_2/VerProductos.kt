package com.example.prueba_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_2.Adapter.AdapterProducto
import com.example.prueba_2.Models.Producto
import com.example.prueba_2.databinding.ActivityVerProductosBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VerProductos : AppCompatActivity() {
    private lateinit var binding: ActivityVerProductosBinding
    //lista de productos
    private lateinit var productosList : ArrayList<Producto>
    private lateinit var productosReciclerView: RecyclerView
    //firebase
    private lateinit var database : DatabaseReference

    private lateinit var adapterProducto: AdapterProducto





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productosReciclerView = binding.rvProductos
        //productosReciclerView = findViewById(R.id.rvProductos)
        productosReciclerView.layoutManager = LinearLayoutManager(this)
        productosReciclerView.setHasFixedSize(true)
        productosList = arrayListOf<Producto>()
        getProductos()
    }

    private fun getProductos() {
        database = FirebaseDatabase.getInstance().getReference("Productos")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (productosSnapshot in snapshot.children){
                        val producto = productosSnapshot.getValue(Producto::class.java)
                        productosList.add(producto!!)
                    }
                    adapterProducto = AdapterProducto(productosList)
                    productosReciclerView.adapter = adapterProducto
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }) 
    }
}