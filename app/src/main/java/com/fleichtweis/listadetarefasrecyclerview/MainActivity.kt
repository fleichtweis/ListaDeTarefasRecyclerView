package com.fleichtweis.listadetarefasrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editTitulo: EditText
    private lateinit var editData: EditText
    private lateinit var radioGroupTipo: RadioGroup
    private lateinit var radioTrabalho: RadioButton
    private lateinit var radioPessoal: RadioButton
    private lateinit var textTarefas: TextView
    private lateinit var btnAdicionar: Button

    //RecyclerView
    private lateinit var rvTarefas: RecyclerView
    private lateinit var tarefaAdapter: TarefaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTitulo = findViewById(R.id.edit_titulo)
        editData = findViewById(R.id.edit_data)
        radioGroupTipo = findViewById(R.id.radio_group)
        radioTrabalho = findViewById(R.id.radio_trabalho)
        radioPessoal = findViewById(R.id.radio_pessoal)
        btnAdicionar = findViewById(R.id.btn_adicionar)
        rvTarefas = findViewById(R.id.rv_tarefas)


        //Configurar RecyclerView
        //Adapter
        tarefaAdapter = TarefaAdapter {
            tarefaAdapter.excluirTarefa(it)
        }
        rvTarefas.adapter = tarefaAdapter

        //LayoutManager
        rvTarefas.layoutManager = LinearLayoutManager(this)


        //Salvar uma tarefa
        btnAdicionar.setOnClickListener {
            salvarTarefa()
        }


    }

    private fun limparFormulario(){
        editTitulo.setText("")
        editData.setText("")
        radioGroupTipo.clearCheck()

    }


    private fun salvarTarefa(){
        if (validarCampos()){

            //Mutablelist (Tarefas)
            val titulo = editTitulo.text.toString()
            val data = editData.text.toString()
            val tipo = if (radioTrabalho.isChecked) "trabalho" else "pessoal"
            val tarefa = Tarefa(titulo, data, tipo)

            //listaTarefas.add(tarefa)
            tarefaAdapter.adicionarTarefa(tarefa)
            //exibirTarefas()
            limparFormulario()
        }
    }

    private fun validarCampos(): Boolean{
        if (editTitulo.text.isEmpty()){
            exibirMensagem("Preencha um título para a tarefa.")
            return false
        }
        if (editData.text.isEmpty()){
            exibirMensagem("Preencha uma data para a tarefa.")
            return false
        }
        if (!radioTrabalho.isChecked && !radioPessoal.isChecked ){
            exibirMensagem("Preencha um tipo para a tarefa.")
            return false
        }

        return true
    }

    private fun exibirMensagem(mensagem: String){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }

}