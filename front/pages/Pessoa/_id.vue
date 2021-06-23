<template>
    <div>
        <h1>Cadastro de Pessoa</h1>
        <form @submit.prevent="save()">
            <div class="p-fluid">
                <div class="p-field">
                    <label for="nome">Nome</label>
                    <inputText id="nome" name="nome" v-model="pessoa.nome" type="text" />
                </div>
                <div class="p-field">
                    <label for="sobrenome">Sobrenome</label>
                    <inputText id="sobrenome" v-model="pessoa.sobrenome" type="text" />
                </div>
            </div>
            <Button  type="submit" label="Salvar" class="p-button-secondary" />
        </form>
    </div>
</template>

<script>
import PessoaService from '~/service/PessoaService.js'

export default {
    layout: "default",
    async asyncData({params, error}){
        return {
            id: params.id,
            pessoa: {
                id: null,
                nome: '',
                sobrenome: ''
            }
        }
    },
    pessoaService: null,
    created(){
        this.pessoaService = new PessoaService(this.$axios);
    },
    mounted(){
        if(this.id != "new"){
            this.getPessoa(this.id);
        }
    },
    methods: {
        async getPessoa(id){
            let response = await this.pessoaService.byId(id);
            if(response.id){
                this.pessoa = response;
            }
        },
        async save(){
            console.log(this.pessoa)
            let values = this.pessoa;
            this.pessoaService.save(values).then((response) => { this.$toast.add({severity: "success", detail: "sucesso!", life:5000});
            this.$router.push("/pessoa");
            })
            .catch((error) => {
                this.$toast.add({severity: "error", detail: "merda!", life:5000});
            });
            
        }
    }

}


</script>
