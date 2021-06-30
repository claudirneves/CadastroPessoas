<template>
    <div>
        <Toolbar class="p-mb-4">
            <template #left>
                <Button id="btn-novo" label="Novo Utensílio" icon="pi pi-plus" class="p-button-success p-mr-2" @click="link()" />
                <Button label="Voltar" icon="pi pi-plus" class="p-button" @click="voltar()"/>
            </template>
        </Toolbar>

        
        <DataTable :value="utensilios" responsiveLayout="scroll">
           
            <Column field="descricao" header="Descrição"></Column>
            <Column field="disponivel" header="Disponível"></Column>
            <Column header="Ações">
                <template #body="slotProps">
                    <Button label="Editar" @click="edit(slotProps)" class="p-button-text" />
                    <Button label="Excluir" @click="del(slotProps)" class="p-button-text p-button-danger" />
                </template>
            </Column>
        </DataTable>
        
    </div>
</template>


<script>
import UtensilioService from '~/service/UtensilioService'

export default {
    layout: 'default',
    data(){
        return {
            utensilios: []
        }
    },
    utensilioService: null,
    created(){
        this.utensilioService = new UtensilioService(this.$axios);
    },
    mounted(){
        this.utensilioList();
    },
    methods: {
        async utensilioList(){
            let response = await this.utensilioService.list();
            this.utensilios = response.content;
        },
        voltar(){
            this.$router.push('./')
        },
        edit(row){
            this.$router.push('/utensilio/'+row.data.id);
        },
        link(){
            this.$router.push('/utensilio/new');
        },
        async del(row){
            var retorno;
            retorno = confirm('Clique OK para excluir');
            if(retorno==true){
                try{
                    let response = await this.utensilioService.delete(row.data.id);
                    this.utensilioList();
                    this.$toast.add({severity: 'success', detail: 'Utensílio Deletado com Sucesso!', life:5000});
                }catch(err){
                    this.$toast.add({severity: 'error', detail: 'Não foi possível deletar o utensílio!',life:5000});
                }
            }
        }
    }
}
</script>
<style scoped>
  #btn-novo {
    margin-right: 30px;
  }

</style>