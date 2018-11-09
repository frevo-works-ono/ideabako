<template>
  <v-container
    fluid
    grid-list-md
  >
  <v-layout row wrap>
    <v-flex v-for="idea in ideas" xs12 sm6 md4 :key="idea.id">
      <v-card>
        <v-container
          fill-height
          fluid
          pa-2
        >
        <ul>
          <li>{{idea.goal}}したい</li>
          <li>{{idea.target}}向けの</li>
          <li>{{idea.productName}}というプロダクトは、</li>
          <li>{{idea.category}}です。</li>
          <li>これは{{idea.merit}}ができ、</li>
          <li>{{idea.conflict}}と違って、</li>
          <li>{{idea.diff}}が備わっている。</li>
        </ul>
        </v-container>
        <v-card-actions>
          登録者：{{idea.userName}} <br>最終更新日：{{idea.updateDateStr}}  
          <v-spacer></v-spacer>
          <v-btn icon @click="edit(idea.id)">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="remove(idea.id)">
            <v-icon>delete</v-icon>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
    <v-fab-transition>
      <v-btn
        fab
        dark
        fixed
        bottom
        right
        color="pink"
        @click="dialogFormVisible = true"
      >
        <v-icon>add</v-icon>
      </v-btn>
    </v-fab-transition>
    <v-dialog v-model="dialogFormVisible" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="headline">アイデア登録</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-form ref="form" v-model="valid" lazy-validation>
            <v-layout wrap>
              
              <v-flex xs6>
                <v-text-field label="氏名" required v-model="form.userName" :rules="requiredRules"></v-text-field>
              </v-flex>
              <v-flex xs6>
                <v-text-field label="製品名" required v-model="form.productName" :rules="requiredRules"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="製品カテゴリ" required v-model="form.category" :rules="requiredRules"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="目的" required v-model="form.goal" :rules="requiredRules"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="ターゲット" required v-model="form.target" :rules="requiredRules"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="メリット" required v-model="form.merit" :rules="requiredRules"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="競合製品" required v-model="form.conflict" :rules="requiredRules"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="差別化ポイント" required v-model="form.diff" :rules="requiredRules"></v-text-field>
              </v-flex>
              
            </v-layout>
            </v-form>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click="clearForm">キャンセル</v-btn>
          <v-btn color="blue darken-1" flat @click="save" :disabled="!valid">保存</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</v-container>
</template>

<script>
  export default {
    async asyncData({app}){
      const ideas = await app.$axios.$get('/api/ideas')
      return {ideas}
    },
    data() {
      return {
        dialogFormVisible:false,
        formLabelWidth: '120px',
        form:{
          id:null,
          goal:null,
          target:null,
          productName:null,
          category:null,
          merit:null,
          conflict:null,
          diff:null,
          userName:null
        },
        valid:true,
        requiredRules: [
        v => !!v || '入力してください'
      ],
      }
    },
    methods:{
      async save(){
        if (!this.$refs.form.validate()) {
          return;
        }

        if(this.form.id){
          await this.$axios.$put(`/api/ideas/${this.form.id}`,this.form)
        }else{
          await this.$axios.$post('/api/ideas',this.form)
        }
        this.ideas = await this.$axios.$get('/api/ideas')
        this.clearForm()        
      },
      async edit(id){
        const idea = await this.$axios.$get(`/api/ideas/${id}`,this.form)
        this.form.goal = idea.goal;
        this.form.target = idea.target
        this.form.productName = idea.productName
        this.form.category = idea.category
        this.form.merit = idea.merit
        this.form.conflict = idea.conflict
        this.form.diff = idea.diff
        this.form.userName = idea.userName
        this.form.id = idea.id
        this.dialogFormVisible = true
      },
      clearForm(){
        this.$refs.form.reset()
        this.form.id = null
        this.form.goal = null;
        this.form.target = null;
        this.form.productName = null;
        this.form.category = null;
        this.form.merit = null;
        this.form.conflict = null;
        this.form.diff = null;
        this.form.userName = null;
        this.dialogFormVisible = false
      },
      async remove(id){
        if(confirm('削除します。よろしいですか？')){
          await this.$axios.$delete(`/api/ideas/${id}`)
          this.ideas = this.ideas.filter(idea => idea.id !== id)
        }
      }
    }
  }
</script>
