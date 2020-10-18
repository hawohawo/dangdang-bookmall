<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="出版社" prop="publisher">
      <el-input v-model="dataForm.publisher" placeholder="出版社"></el-input>
    </el-form-item>
    <el-form-item label="出版时间" prop="publishTime">
      <el-input v-model="dataForm.publishTime" placeholder="出版时间"></el-input>
    </el-form-item>
    <el-form-item label="ISBN" prop="isbn">
      <el-input v-model="dataForm.isbn" placeholder="ISBN"></el-input>
    </el-form-item>
    <el-form-item label="字数" prop="wordNum">
      <el-input v-model="dataForm.wordNum" placeholder="字数"></el-input>
    </el-form-item>
    <el-form-item label="开本" prop="size">
      <el-input v-model="dataForm.size" placeholder="开本"></el-input>
    </el-form-item>
    <el-form-item label="纸张" prop="paperType">
      <el-input v-model="dataForm.paperType" placeholder="纸张"></el-input>
    </el-form-item>
    <el-form-item label="包装" prop="pack">
      <el-input v-model="dataForm.pack" placeholder="包装"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          publisher: '',
          publishTime: '',
          isbn: '',
          wordNum: '',
          size: '',
          paperType: '',
          pack: ''
        },
        dataRule: {
          publisher: [
            { required: true, message: '出版社不能为空', trigger: 'blur' }
          ],
          publishTime: [
            { required: true, message: '出版时间不能为空', trigger: 'blur' }
          ],
          isbn: [
            { required: true, message: 'ISBN不能为空', trigger: 'blur' }
          ],
          wordNum: [
            { required: true, message: '字数不能为空', trigger: 'blur' }
          ],
          size: [
            { required: true, message: '开本不能为空', trigger: 'blur' }
          ],
          paperType: [
            { required: true, message: '纸张不能为空', trigger: 'blur' }
          ],
          pack: [
            { required: true, message: '包装不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/product/publish/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.publisher = data.publish.publisher
                this.dataForm.publishTime = data.publish.publishTime
                this.dataForm.isbn = data.publish.isbn
                this.dataForm.wordNum = data.publish.wordNum
                this.dataForm.size = data.publish.size
                this.dataForm.paperType = data.publish.paperType
                this.dataForm.pack = data.publish.pack
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/product/publish/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'publisher': this.dataForm.publisher,
                'publishTime': this.dataForm.publishTime,
                'isbn': this.dataForm.isbn,
                'wordNum': this.dataForm.wordNum,
                'size': this.dataForm.size,
                'paperType': this.dataForm.paperType,
                'pack': this.dataForm.pack
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
