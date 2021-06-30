<template>
  <div>
    <Toolbar class="p-mb-4">
      <template #left>
        <Button label="Lista de Pessoas" icon="pi pi-plus" class="p-button" @click="pessoa()" id="pessoa"/>
         <Button label="Lista de Utensílios" icon="pi pi-plus" class="p-button" @click="utensilio()" id="utensilio" />
      </template>
    </Toolbar>
  </div>
  
</template>

<script>

export default {
  methods:{
    pessoa(){
    this.$router.push('./pessoa')
    },
    utensilio(){
      this.$router.push('./utensilio')
    }
  }
  
}
</script>

<style scoped>
  #pessoa {
    margin-right: 30px;
  }
</style>
