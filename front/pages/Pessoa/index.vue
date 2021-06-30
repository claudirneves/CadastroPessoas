<template>
    <div>
        <Toolbar class="p-mb-4">
            <template #left>
                <Button id="btn-novo" label="Nova Pessoa" icon="pi pi-plus" class="p-button-success p-mr-2" @click="link()" />
                <Button label="Voltar" icon="pi pi-plus" class="p-button" @click="voltar()"/>
                <form method="get">
                    <label id="lb-nome" for="nome">Buscar:</label>
                    <inputText id="nome" v-model="pessoas.nome" type="search" />
                    <Button label="Buscar" icon="pi pi-plus" class="p-button p-mr-2" @click="getNome('nome')" />
                </form>
                
            </template>
        </Toolbar>
        <DataTable :value="pessoas" responsiveLayout="scroll">
            <Column field="nome" header="Nome"></Column>
            <Column field="sobrenome" header="Sobrenome"></Column>
            <Column header="Ações">
                <template #body="slotProps">
                    <Button label="Editar" @click="edit(slotProps)" class="p-button-text" />
                    <Button label="Excluir" @click="openDelete(slotProps)" class="p-button-text p-button-danger" />
                </template>
            </Column>
        </DataTable>
        
    </div>
</template>

<script>
    import PessoaService from '~/service/PessoaService'
    export default {
        layout: 'default',
        data(){
            return {
                pessoas: []
            }
        },
        pessoaService: null,
        created(){
            this.pessoaService = new PessoaService(this.$axios);
        },
        mounted(){
            this.pessoaList();
        },
       
        
        methods: {
            async pessoaList(){
                let response = await this.pessoaService.list();
                this.pessoas = response.content;
            },
            
            voltar(){
                this.$router.push('./')
            },
            async getNome(nome){
                let response = await this.pessoaService.byNome(nome);
                this.pessoas = response.content;
                
            },
            link(){
                this.$router.push('/pessoa/new');
            },
            edit(row){
                this.$router.push('/pessoa/'+row.data.id);
            },
            async openDelete(row){
                var retorno;
                retorno = confirm("Clique OK para confimar a exclusão");
                if(retorno==true){
                    try{
                        let response = await this.pessoaService.delete(row.data.id);
                        this.pessoaList();
                        this.$toast.add({severity:'success', detail: 'Pessoa Deletada com Sucesso!',life:3000});
                    }catch(err){
                        this.$toast.add({severity:'error', detail: 'erro!',life:3000});
                    }
                
                }
            }
        }
    }
</script>

<style scoped>
    #lb-nome {
        margin-right: 2px;
        margin-left: 575px;
    }

    #nome{
        margin-right: 15px;
    }
    #btn-novo {
       margin-right: 15px; 
    }
</style>
