<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="couponId">
      <el-input v-model="dataForm.couponId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="code">
      <el-input v-model="dataForm.code" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userName">
      <el-input v-model="dataForm.userName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="getTime">
      <el-input v-model="dataForm.getTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="0 未使用 1 已使用" prop="status">
      <el-input v-model="dataForm.status" placeholder="0 未使用 1 已使用"></el-input>
    </el-form-item>
    <el-form-item label="" prop="useTime">
      <el-input v-model="dataForm.useTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="orderinfoCode">
      <el-input v-model="dataForm.orderinfoCode" placeholder=""></el-input>
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
          couponId: '',
          code: '',
          userName: '',
          getTime: '',
          status: '',
          useTime: '',
          orderinfoCode: ''
        },
        dataRule: {
          couponId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          code: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          getTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '0 未使用 1 已使用不能为空', trigger: 'blur' }
          ],
          useTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          orderinfoCode: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/promotion/couponuser/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.couponId = data.couponUser.couponId
                this.dataForm.code = data.couponUser.code
                this.dataForm.userName = data.couponUser.userName
                this.dataForm.getTime = data.couponUser.getTime
                this.dataForm.status = data.couponUser.status
                this.dataForm.useTime = data.couponUser.useTime
                this.dataForm.orderinfoCode = data.couponUser.orderinfoCode
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
              url: this.$http.adornUrl(`/promotion/couponuser/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'couponId': this.dataForm.couponId,
                'code': this.dataForm.code,
                'userName': this.dataForm.userName,
                'getTime': this.dataForm.getTime,
                'status': this.dataForm.status,
                'useTime': this.dataForm.useTime,
                'orderinfoCode': this.dataForm.orderinfoCode
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
