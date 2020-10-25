<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="收货人" prop="receiveName">
      <el-input v-model="dataForm.receiveName" placeholder="收货人"></el-input>
    </el-form-item>
    <el-form-item label="联系电话" prop="receiveTel">
      <el-input v-model="dataForm.receiveTel" placeholder="联系电话"></el-input>
    </el-form-item>
    <el-form-item label="收货地址" prop="receiveAddr">
      <el-input v-model="dataForm.receiveAddr" placeholder="收货地址"></el-input>
    </el-form-item>
    <el-form-item label="邮政编码" prop="postal">
      <el-input v-model="dataForm.postal" placeholder="邮政编码"></el-input>
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
          receiveName: '',
          receiveTel: '',
          receiveAddr: '',
          postal: ''
        },
        dataRule: {
          receiveName: [
            { required: true, message: '收货人不能为空', trigger: 'blur' }
          ],
          receiveTel: [
            { required: true, message: '联系电话不能为空', trigger: 'blur' }
          ],
          receiveAddr: [
            { required: true, message: '收货地址不能为空', trigger: 'blur' }
          ],
          postal: [
            { required: true, message: '邮政编码不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/receive/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.receiveName = data.receive.receiveName
                this.dataForm.receiveTel = data.receive.receiveTel
                this.dataForm.receiveAddr = data.receive.receiveAddr
                this.dataForm.postal = data.receive.postal
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
              url: this.$http.adornUrl(`/order/receive/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'receiveName': this.dataForm.receiveName,
                'receiveTel': this.dataForm.receiveTel,
                'receiveAddr': this.dataForm.receiveAddr,
                'postal': this.dataForm.postal
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
