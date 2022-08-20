package com.fleichtweis.listadetarefasrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TarefaAdapter(private val onDeleteClick: (Int) -> Unit): RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private val listaTarefas = mutableListOf<Tarefa>()

    inner class TarefaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val textTitulo: TextView
        private val textTipo: TextView
        private val textData: TextView
        private val btnExcluir: Button

        init {
            textTitulo = itemView.findViewById(R.id.text_titulo)
            textTipo = itemView.findViewById(R.id.text_tipo)
            textData = itemView.findViewById(R.id.text_data)
            btnExcluir = itemView.findViewById(R.id.btn_excluir)
        }

        fun bind(tarefa: Tarefa, position: Int, onDeleteClick: (Int) -> Unit){//Conectar, Ligar
            textTitulo.text = tarefa.titulo
            textTipo.text = tarefa.tipo
            textData.text = tarefa.data

            btnExcluir.setOnClickListener {
                onDeleteClick(position)
            }

        }
    }

    //Cria objeto View do XML do item de lista (Item_tarefa.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tarefa, parent, false)

        return TarefaViewHolder(itemView)
    }

    //Conecta dados com a interface
    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {

        val tarefa = listaTarefas[position]
        holder.bind(tarefa, position, onDeleteClick)

    }

    //Retorna a quantidade de itens da lista
    override fun getItemCount(): Int {
        return listaTarefas.size
    }

    fun adicionarTarefa( tarefa: Tarefa){
        listaTarefas.add(tarefa)
        notifyItemInserted(listaTarefas.size - 1)
    }

    fun excluirTarefa(position: Int){
        listaTarefas.removeAt(position)
        //notifyItemRemoved(position)
        notifyDataSetChanged()
    }

}