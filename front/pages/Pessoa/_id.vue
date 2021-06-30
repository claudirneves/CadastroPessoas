<template>
    <div>
        <Toolbar class="p-mb-4">
            <template #left>
                <Button label="Voltar" icon="pi pi-plus" class="p-button" @click="voltar()" />
            </template>
        </Toolbar>
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
            <Button id="btn-salvar" type="submit" label="Salvar" class="success" />
        </form>
    </div>
</template>

<script>
import PessoaService from '~/service/PessoaService.js'

export default {
    layout: 'default',
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
        if(this.id != 'new'){
            this.getPessoa(this.id);
        }
    },
    methods: {
        voltar(){
            this.$router.push('../pessoa')
        },
        async getPessoa(id){
            let response = await this.pessoaService.byId(id);
            if(response.id){
                this.pessoa = response;
            }
        },
        async save(){
            let values = this.pessoa;
            this.pessoaService.save(values).then((response) => { 
            this.$toast.add({severity: 'success', detail: 'sucesso!', life:5000});
            this.$router.push('/pessoa');
            })
            .catch((error) => {
                this.$toast.add({severity: 'error', detail: 'merda!', life:5000});
            });
            
        }
    }

}


</script>

<style scoped>
    #btn-salvar {
        margin: 15px;
    }
</style>
