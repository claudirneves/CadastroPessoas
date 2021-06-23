export default class PessoaService {
    constructor(axios){
        this.http = axios;
    }

    save(values){
        console.log(values)
        if(values.id == null || values.id == ''){
            return this.http.$post('/pessoa', values);
        }else{
            return this.http.$put('/pessoa/'+values.id, values);
        }
        
    }
    list(){
        return this.http.$get('/pessoa');
    }
    byId(id){
        return this.http.$get('/pessoa/'+id);
    }
    delete(id){
        return this.http.$delete('/pessoa/'+id);
    }
}