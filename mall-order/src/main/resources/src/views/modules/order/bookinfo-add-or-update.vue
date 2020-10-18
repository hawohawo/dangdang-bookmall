<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="所属订单编号" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder="所属订单编号"></el-input>
    </el-form-item>
    <el-form-item label="图书id" prop="bookId">
      <el-input v-model="dataForm.bookId" placeholder="图书id"></el-input>
    </el-form-item>
    <el-form-item label="图书名称" prop="bookName">
      <el-input v-model="dataForm.bookName" placeholder="图书名称"></el-input>
    </el-form-item>
    <el-form-item label="图书图片" prop="bookPic">
      <el-input v-model="dataForm.bookPic" placeholder="图书图片"></el-input>
    </el-form-item>
    <el-form-item label="购买数量" prop="bookNum">
      <el-input v-model="dataForm.bookNum" placeholder="购买数量"></el-input>
    </el-form-item>
    <el-form-item label="付款价格" prop="bookPrice">
      <el-input v-model="dataForm.bookPrice" placeholder="付款价格"></el-input>
    </el-form-item>
    <el-form-item label="图书状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="图书状态"></el-input>
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
          orderId: '',
          bookId: '',
          bookName: '',
          bookPic: '',
          bookNum: '',
          bookPrice: '',
          status: ''
        },
        dataRule: {
          orderId: [
            { required: true, message: '所属订单编号不能为空', trigger: 'blur' }
          ],
          bookId: [
            { required: true, message: '图书id不能为空', trigger: 'blur' }
          ],
          bookName: [
            { required: true, message: '图书名称不能为空', trigger: 'blur' }
          ],
          bookPic: [
            { required: true, message: '图书图片不能为空', trigger: 'blur' }
          ],
          bookNum: [
            { required: true, message: '购买数量不能为空', trigger: 'blur' }
          ],
          bookPrice: [
            { required: true, message: '付款价格不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '图书状态不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/bookinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderId = data.bookinfo.orderId
                this.dataForm.bookId = data.bookinfo.bookId
                this.dataForm.bookName = data.bookinfo.bookName
                this.dataForm.bookPic = data.bookinfo.bookPic
                this.dataForm.bookNum = data.bookinfo.bookNum
                this.dataForm.bookPrice = data.bookinfo.bookPrice
                this.dataForm.status = data.bookinfo.status
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
              url: this.$http.adornUrl(`/order/bookinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'orderId': this.dataForm.orderId,
                'bookId': this.dataForm.bookId,
                'bookName': this.dataForm.bookName,
                'bookPic': this.dataForm.bookPic,
                'bookNum': this.dataForm.bookNum,
                'bookPrice': this.dataForm.bookPrice,
                'status': this.dataForm.status
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
