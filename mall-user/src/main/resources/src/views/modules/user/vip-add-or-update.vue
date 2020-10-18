<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userId">
      <el-input v-model="dataForm.userId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="0.普通会员 1倍
1.银会员 1.2倍
2.金会员 1.5倍
每800成长值升级一个段。成长值获取的积分是普通积分的n倍" prop="level">
      <el-input v-model="dataForm.level" placeholder="0.普通会员 1倍
1.银会员 1.2倍
2.金会员 1.5倍
每800成长值升级一个段。成长值获取的积分是普通积分的n倍"></el-input>
    </el-form-item>
    <el-form-item label="" prop="growth">
      <el-input v-model="dataForm.growth" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="score">
      <el-input v-model="dataForm.score" placeholder=""></el-input>
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
          userId: '',
          level: '',
          growth: '',
          score: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          level: [
            { required: true, message: '0.普通会员 1倍
1.银会员 1.2倍
2.金会员 1.5倍
每800成长值升级一个段。成长值获取的积分是普通积分的n倍不能为空', trigger: 'blur' }
          ],
          growth: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          score: [
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
              url: this.$http.adornUrl(`/user/vip/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.vip.userId
                this.dataForm.level = data.vip.level
                this.dataForm.growth = data.vip.growth
                this.dataForm.score = data.vip.score
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
              url: this.$http.adornUrl(`/user/vip/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'level': this.dataForm.level,
                'growth': this.dataForm.growth,
                'score': this.dataForm.score
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
