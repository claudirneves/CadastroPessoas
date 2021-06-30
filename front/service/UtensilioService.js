export default class UtensilioService {
    constructor(axios){
        this.http = axios;
    }

    save(values){
        if(values.id == null || values.id == ''){
            return this.http.$post('/utensilio',values);
        }else{
            return this.http.$put('/utensilio/'+values.id,values);
        }
    }
    list(){
        return this.http.$get('/utensilio');
    }
    byId(id){
        return this.http.$get('/utensilio/'+id);
    }
    delete(id){
        return this.http.$delete('/utensilio/'+id);
    }
}