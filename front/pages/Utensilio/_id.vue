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
                    
                    <label for="descricao">Descrição</label>
                    <inputText id="descricao" v-model="utensilio.descricao" type="text" />
                    <label for="disponivel">Disponível</label>
                    <select name="select" v-model="utensilio.disponivel">
                        <option value="SIM">Sim</option>
                        <option value="NÃO" selected>Não</option>
                    </select>
                </div>
                
                    
                
            </div>
            <Button  id="btn-salvar" type="submit" label="Salvar" class="success" />
        </form>
    </div>
</template>



<script>
import UtensilioService from '~/service/UtensilioService'

export default {
    layout: 'default',
    async asyncData({params, error}){
        return {
            id: params.id,
            utensilio: {
                id: null,
                descricao: '',
                disponivel: ''
                      
            }
        }
    },
    utensilioService: null,
    created(){
        this.utensilioService = new UtensilioService(this.$axios);
    },
    mounted(){
        if(this.id != 'new'){
            this.getUtensilio(this.id);
        }
    },
    methods: {
        async getUtensilio(id){
            let response = await this.utensilioService.byId(id);
            if(response.id){
                this.utensilio = response;
            }
        },
        voltar(){
            this.$router.push('../utensilio')
        },
        async save(){
            let values = this.utensilio;
            this.utensilioService.save(values).then((response) => {
            this.$toast.add({severity:'success', detail: 'Utensilio salvo com sucesso', life:5000});
            this.$router.push('/utensilio');
            })
            .catch((error) => {
                this.$toast.add({severity:'error', detail:'Não foi possível salvar',life:5000});
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
