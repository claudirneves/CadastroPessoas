<template>
    <div>
        <h1>Listagem de Pessoas</h1>
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
        <nuxt-link class="p-button-text" to="/pessoa/new">Nova Pessoa</nuxt-link>
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
                console.log(response.content);
                this.pessoas = response.content;
            },
            edit(row){
                this.$router.push('/pessoa/'+row.data.id);
            },
            async openDelete(row){
                try{
                    let response = await this.pessoaService.delete(row.data.id);
                    this.pessoaList();
                    this.$toast.add({severity:'success', detail: 'deletado com sucesso!',life:3000});
                }catch(err){
                    this.$toast.add({severity:'error', detail: 'erro!',life:3000});
                }
            }
        }
    }
</script>
