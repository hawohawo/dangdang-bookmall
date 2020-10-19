<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="订单编号" prop="code">
      <el-input v-model="dataForm.code" placeholder="订单编号"></el-input>
    </el-form-item>
    <el-form-item label="下单时间" prop="timeXd">
      <el-input v-model="dataForm.timeXd" placeholder="下单时间"></el-input>
    </el-form-item>
    <el-form-item label="订单总价" prop="moneyTotal">
      <el-input v-model="dataForm.moneyTotal" placeholder="订单总价"></el-input>
    </el-form-item>
    <el-form-item label="订单状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="订单状态"></el-input>
    </el-form-item>
    <el-form-item label="物流单号" prop="logisticsNum">
      <el-input v-model="dataForm.logisticsNum" placeholder="物流单号"></el-input>
    </el-form-item>
    <el-form-item label="收货地址" prop="address">
      <el-input v-model="dataForm.address" placeholder="收货地址"></el-input>
    </el-form-item>
    <el-form-item label="用户联系方式" prop="userPhone">
      <el-input v-model="dataForm.userPhone" placeholder="用户联系方式"></el-input>
    </el-form-item>
    <el-form-item label="收货人" prop="userName">
      <el-input v-model="dataForm.userName" placeholder="收货人"></el-input>
    </el-form-item>
    <el-form-item label="邮政编码" prop="postal">
      <el-input v-model="dataForm.postal" placeholder="邮政编码"></el-input>
    </el-form-item>
    <el-form-item label="订单备注" prop="remark">
      <el-input v-model="dataForm.remark" placeholder="订单备注"></el-input>
    </el-form-item>
    <el-form-item label="支付方式" prop="paymentType">
      <el-input v-model="dataForm.paymentType" placeholder="支付方式"></el-input>
    </el-form-item>
    <el-form-item label="支付时间" prop="paymentTime">
      <el-input v-model="dataForm.paymentTime" placeholder="支付时间"></el-input>
    </el-form-item>
    <el-form-item label="发货时间" prop="deliverTime">
      <el-input v-model="dataForm.deliverTime" placeholder="发货时间"></el-input>
    </el-form-item>
    <el-form-item label="确认收货时间" prop="reciveTime">
      <el-input v-model="dataForm.reciveTime" placeholder="确认收货时间"></el-input>
    </el-form-item>
    <el-form-item label="运费" prop="freight">
      <el-input v-model="dataForm.freight" placeholder="运费"></el-input>
    </el-form-item>
    <el-form-item label="积分抵扣" prop="discountScore">
      <el-input v-model="dataForm.discountScore" placeholder="积分抵扣"></el-input>
    </el-form-item>
    <el-form-item label="优惠券抵扣" prop="discountCoupon">
      <el-input v-model="dataForm.discountCoupon" placeholder="优惠券抵扣"></el-input>
    </el-form-item>
    <el-form-item label="自动收货时间" prop="autorecive">
      <el-input v-model="dataForm.autorecive" placeholder="自动收货时间"></el-input>
    </el-form-item>
    <el-form-item label="" prop="userId">
      <el-input v-model="dataForm.userId" placeholder=""></el-input>
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
          timeXd: '',
          moneyTotal: '',
          status: '',
          logisticsNum: '',
          address: '',
          userPhone: '',
          userName: '',
          postal: '',
          remark: '',
          paymentType: '',
          paymentTime: '',
          deliverTime: '',
          reciveTime: '',
          freight: '',
          discountScore: '',
          discountCoupon: '',
          autorecive: '',
          userId: ''
        },
        dataRule: {
          code: [
            { required: true, message: '订单编号不能为空', trigger: 'blur' }
          ],
          timeXd: [
            { required: true, message: '下单时间不能为空', trigger: 'blur' }
          ],
          moneyTotal: [
            { required: true, message: '订单总价不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '订单状态不能为空', trigger: 'blur' }
          ],
          logisticsNum: [
            { required: true, message: '物流单号不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '收货地址不能为空', trigger: 'blur' }
          ],
          userPhone: [
            { required: true, message: '用户联系方式不能为空', trigger: 'blur' }
          ],
          userName: [
            { required: true, message: '收货人不能为空', trigger: 'blur' }
          ],
          postal: [
            { required: true, message: '邮政编码不能为空', trigger: 'blur' }
          ],
          remark: [
            { required: true, message: '订单备注不能为空', trigger: 'blur' }
          ],
          paymentType: [
            { required: true, message: '支付方式不能为空', trigger: 'blur' }
          ],
          paymentTime: [
            { required: true, message: '支付时间不能为空', trigger: 'blur' }
          ],
          deliverTime: [
            { required: true, message: '发货时间不能为空', trigger: 'blur' }
          ],
          reciveTime: [
            { required: true, message: '确认收货时间不能为空', trigger: 'blur' }
          ],
          freight: [
            { required: true, message: '运费不能为空', trigger: 'blur' }
          ],
          discountScore: [
            { required: true, message: '积分抵扣不能为空', trigger: 'blur' }
          ],
          discountCoupon: [
            { required: true, message: '优惠券抵扣不能为空', trigger: 'blur' }
          ],
          autorecive: [
            { required: true, message: '自动收货时间不能为空', trigger: 'blur' }
          ],
          userId: [
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
              url: this.$http.adornUrl(`/order/orderinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.code = data.orderinfo.code
                this.dataForm.timeXd = data.orderinfo.timeXd
                this.dataForm.moneyTotal = data.orderinfo.moneyTotal
                this.dataForm.status = data.orderinfo.status
                this.dataForm.logisticsNum = data.orderinfo.logisticsNum
                this.dataForm.address = data.orderinfo.address
                this.dataForm.userPhone = data.orderinfo.userPhone
                this.dataForm.userName = data.orderinfo.userName
                this.dataForm.postal = data.orderinfo.postal
                this.dataForm.remark = data.orderinfo.remark
                this.dataForm.paymentType = data.orderinfo.paymentType
                this.dataForm.paymentTime = data.orderinfo.paymentTime
                this.dataForm.deliverTime = data.orderinfo.deliverTime
                this.dataForm.reciveTime = data.orderinfo.reciveTime
                this.dataForm.freight = data.orderinfo.freight
                this.dataForm.discountScore = data.orderinfo.discountScore
                this.dataForm.discountCoupon = data.orderinfo.discountCoupon
                this.dataForm.autorecive = data.orderinfo.autorecive
                this.dataForm.userId = data.orderinfo.userId
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
              url: this.$http.adornUrl(`/order/orderinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'code': this.dataForm.code,
                'timeXd': this.dataForm.timeXd,
                'moneyTotal': this.dataForm.moneyTotal,
                'status': this.dataForm.status,
                'logisticsNum': this.dataForm.logisticsNum,
                'address': this.dataForm.address,
                'userPhone': this.dataForm.userPhone,
                'userName': this.dataForm.userName,
                'postal': this.dataForm.postal,
                'remark': this.dataForm.remark,
                'paymentType': this.dataForm.paymentType,
                'paymentTime': this.dataForm.paymentTime,
                'deliverTime': this.dataForm.deliverTime,
                'reciveTime': this.dataForm.reciveTime,
                'freight': this.dataForm.freight,
                'discountScore': this.dataForm.discountScore,
                'discountCoupon': this.dataForm.discountCoupon,
                'autorecive': this.dataForm.autorecive,
                'userId': this.dataForm.userId
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
