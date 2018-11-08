<template>
  <v-layout row wrap>
    <v-flex v-for="idea in ideas" xs4 :key="idea.id">
      <v-card>
        <ul>
          <li>{{idea.goal}}したい</li>
          <li>{{idea.target}}向けの</li>
          <li>{{idea.productName}}というプロダクトは、</li>
          <li>{{idea.category}}です。</li>
          <li>これは{{idea.merit}}ができ、</li>
          <li>{{idea.conflict}}と違って、</li>
          <li>{{idea.diff}}が備わっている。</li>
        </ul>
        
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn icon>
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>delete</v-icon>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>

    <v-dialog v-model="dialogFormVisible" persistent max-width="600px">
      <v-btn slot="activator" color="primary" dark>Open Dialog</v-btn>
      <v-card>
        <v-card-title>
          <span class="headline">アイデア登録</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-flex xs12>
                <v-text-field label="氏名" required v-model="form.userName"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="製品名" required v-model="form.productName"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="製品カテゴリ" required v-model="form.category"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="目的" required v-model="form.goal"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="ターゲット" required v-model="form.target"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="メリット" required v-model="form.merit"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="競合製品" required v-model="form.conflict"></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="差分" required v-model="form.diff"></v-text-field>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click.native="dialogFormVisible = false">Close</v-btn>
          <v-btn color="blue darken-1" flat @click="save">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
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
          goal:null,
          target:null,
          productName:null,
          category:null,
          merit:null,
          conflict:null,
          diff:null,
          userName:null
        }
      }
    },
    methods:{
      async save(){
        const idea = await this.$axios.$post('/api/ideas',this.form)
        this.ideas.push(idea)
        this.clearForm()        
      },
      clearForm(){
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
      remove(id){
        this.$axios.$delete(`/api/ideas/${id}`).then(res => {
          this.$message({
            message: '削除成功',
            type: 'success'
          });
        })
      }
    }
  }
</script>
