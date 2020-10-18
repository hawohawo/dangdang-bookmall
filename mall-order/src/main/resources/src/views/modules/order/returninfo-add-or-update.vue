<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="退货编号" prop="code">
      <el-input v-model="dataForm.code" placeholder="退货编号"></el-input>
    </el-form-item>
    <el-form-item label="所属订单编号" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder="所属订单编号"></el-input>
    </el-form-item>
    <el-form-item label="退货时间" prop="time">
      <el-input v-model="dataForm.time" placeholder="退货时间"></el-input>
    </el-form-item>
    <el-form-item label="图书id" prop="bookId">
      <el-input v-model="dataForm.bookId" placeholder="图书id"></el-input>
    </el-form-item>
    <el-form-item label="图书名称" prop="bookName">
      <el-input v-model="dataForm.bookName" placeholder="图书名称"></el-input>
    </el-form-item>
    <el-form-item label="退款数量" prop="bookNum">
      <el-input v-model="dataForm.bookNum" placeholder="退款数量"></el-input>
    </el-form-item>
    <el-form-item label="退款金额" prop="bookPrice">
      <el-input v-model="dataForm.bookPrice" placeholder="退款金额"></el-input>
    </el-form-item>
    <el-form-item label="退款订单状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="退款订单状态"></el-input>
    </el-form-item>
    <el-form-item label="退款理由" prop="reason">
      <el-input v-model="dataForm.reason" placeholder="退款理由"></el-input>
    </el-form-item>
    <el-form-item label="问题描述" prop="problem">
      <el-input v-model="dataForm.problem" placeholder="问题描述"></el-input>
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
          code: '',
          orderId: '',
          time: '',
          bookId: '',
          bookName: '',
          bookNum: '',
          bookPrice: '',
          status: '',
          reason: '',
          problem: ''
        },
        dataRule: {
          code: [
            { required: true, message: '退货编号不能为空', trigger: 'blur' }
          ],
          orderId: [
            { required: true, message: '所属订单编号不能为空', trigger: 'blur' }
          ],
          time: [
            { required: true, message: '退货时间不能为空', trigger: 'blur' }
          ],
          bookId: [
            { required: true, message: '图书id不能为空', trigger: 'blur' }
          ],
          bookName: [
            { required: true, message: '图书名称不能为空', trigger: 'blur' }
          ],
          bookNum: [
            { required: true, message: '退款数量不能为空', trigger: 'blur' }
          ],
          bookPrice: [
            { required: true, message: '退款金额不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '退款订单状态不能为空', trigger: 'blur' }
          ],
          reason: [
            { required: true, message: '退款理由不能为空', trigger: 'blur' }
          ],
          problem: [
            { required: true, message: '问题描述不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/returninfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.code = data.returninfo.code
                this.dataForm.orderId = data.returninfo.orderId
                this.dataForm.time = data.returninfo.time
                this.dataForm.bookId = data.returninfo.bookId
                this.dataForm.bookName = data.returninfo.bookName
                this.dataForm.bookNum = data.returninfo.bookNum
                this.dataForm.bookPrice = data.returninfo.bookPrice
                this.dataForm.status = data.returninfo.status
                this.dataForm.reason = data.returninfo.reason
                this.dataForm.problem = data.returninfo.problem
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
              url: this.$http.adornUrl(`/order/returninfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'code': this.dataForm.code,
                'orderId': this.dataForm.orderId,
                'time': this.dataForm.time,
                'bookId': this.dataForm.bookId,
                'bookName': this.dataForm.bookName,
                'bookNum': this.dataForm.bookNum,
                'bookPrice': this.dataForm.bookPrice,
                'status': this.dataForm.status,
                'reason': this.dataForm.reason,
                'problem': this.dataForm.problem
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
