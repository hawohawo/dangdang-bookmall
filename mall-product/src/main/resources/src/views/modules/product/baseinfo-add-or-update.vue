<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="图书图片" prop="picture">
      <el-input v-model="dataForm.picture" placeholder="图书图片"></el-input>
    </el-form-item>
    <el-form-item label="图书分类ID" prop="typeId">
      <el-input v-model="dataForm.typeId" placeholder="图书分类ID"></el-input>
    </el-form-item>
    <el-form-item label="图书名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="图书名称"></el-input>
    </el-form-item>
    <el-form-item label="副标题" prop="fname">
      <el-input v-model="dataForm.fname" placeholder="副标题"></el-input>
    </el-form-item>
    <el-form-item label="图书介绍" prop="introduce">
      <el-input v-model="dataForm.introduce" placeholder="图书介绍"></el-input>
    </el-form-item>
    <el-form-item label="作者" prop="author">
      <el-input v-model="dataForm.author" placeholder="作者"></el-input>
    </el-form-item>
    <el-form-item label="图书评分" prop="score">
      <el-input v-model="dataForm.score" placeholder="图书评分"></el-input>
    </el-form-item>
    <el-form-item label="图书定价" prop="priceDj">
      <el-input v-model="dataForm.priceDj" placeholder="图书定价"></el-input>
    </el-form-item>
    <el-form-item label="图书售价" prop="priceSj">
      <el-input v-model="dataForm.priceSj" placeholder="图书售价"></el-input>
    </el-form-item>
    <el-form-item label="运费" prop="priceYf">
      <el-input v-model="dataForm.priceYf" placeholder="运费"></el-input>
    </el-form-item>
    <el-form-item label="是否上架（0：已上架 1：未上架）" prop="insale">
      <el-input v-model="dataForm.insale" placeholder="是否上架（0：已上架 1：未上架）"></el-input>
    </el-form-item>
    <el-form-item label="图书关键字" prop="keyword">
      <el-input v-model="dataForm.keyword" placeholder="图书关键字"></el-input>
    </el-form-item>
    <el-form-item label="图书库存" prop="stock">
      <el-input v-model="dataForm.stock" placeholder="图书库存"></el-input>
    </el-form-item>
    <el-form-item label="积分" prop="integral">
      <el-input v-model="dataForm.integral" placeholder="积分"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="remarks">
      <el-input v-model="dataForm.remarks" placeholder="备注"></el-input>
    </el-form-item>
    <el-form-item label="出版信息ID" prop="publishId">
      <el-input v-model="dataForm.publishId" placeholder="出版信息ID"></el-input>
    </el-form-item>
    <el-form-item label="图书详情ID" prop="bookdetailId">
      <el-input v-model="dataForm.bookdetailId" placeholder="图书详情ID"></el-input>
    </el-form-item>
    <el-form-item label="评论ID" prop="commentId">
      <el-input v-model="dataForm.commentId" placeholder="评论ID"></el-input>
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
          picture: '',
          typeId: '',
          name: '',
          fname: '',
          introduce: '',
          author: '',
          score: '',
          priceDj: '',
          priceSj: '',
          priceYf: '',
          insale: '',
          keyword: '',
          stock: '',
          integral: '',
          remarks: '',
          publishId: '',
          bookdetailId: '',
          commentId: ''
        },
        dataRule: {
          picture: [
            { required: true, message: '图书图片不能为空', trigger: 'blur' }
          ],
          typeId: [
            { required: true, message: '图书分类ID不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '图书名称不能为空', trigger: 'blur' }
          ],
          fname: [
            { required: true, message: '副标题不能为空', trigger: 'blur' }
          ],
          introduce: [
            { required: true, message: '图书介绍不能为空', trigger: 'blur' }
          ],
          author: [
            { required: true, message: '作者不能为空', trigger: 'blur' }
          ],
          score: [
            { required: true, message: '图书评分不能为空', trigger: 'blur' }
          ],
          priceDj: [
            { required: true, message: '图书定价不能为空', trigger: 'blur' }
          ],
          priceSj: [
            { required: true, message: '图书售价不能为空', trigger: 'blur' }
          ],
          priceYf: [
            { required: true, message: '运费不能为空', trigger: 'blur' }
          ],
          insale: [
            { required: true, message: '是否上架（0：已上架 1：未上架）不能为空', trigger: 'blur' }
          ],
          keyword: [
            { required: true, message: '图书关键字不能为空', trigger: 'blur' }
          ],
          stock: [
            { required: true, message: '图书库存不能为空', trigger: 'blur' }
          ],
          integral: [
            { required: true, message: '积分不能为空', trigger: 'blur' }
          ],
          remarks: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
          ],
          publishId: [
            { required: true, message: '出版信息ID不能为空', trigger: 'blur' }
          ],
          bookdetailId: [
            { required: true, message: '图书详情ID不能为空', trigger: 'blur' }
          ],
          commentId: [
            { required: true, message: '评论ID不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/product/baseinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.picture = data.baseinfo.picture
                this.dataForm.typeId = data.baseinfo.typeId
                this.dataForm.name = data.baseinfo.name
                this.dataForm.fname = data.baseinfo.fname
                this.dataForm.introduce = data.baseinfo.introduce
                this.dataForm.author = data.baseinfo.author
                this.dataForm.score = data.baseinfo.score
                this.dataForm.priceDj = data.baseinfo.priceDj
                this.dataForm.priceSj = data.baseinfo.priceSj
                this.dataForm.priceYf = data.baseinfo.priceYf
                this.dataForm.insale = data.baseinfo.insale
                this.dataForm.keyword = data.baseinfo.keyword
                this.dataForm.stock = data.baseinfo.stock
                this.dataForm.integral = data.baseinfo.integral
                this.dataForm.remarks = data.baseinfo.remarks
                this.dataForm.publishId = data.baseinfo.publishId
                this.dataForm.bookdetailId = data.baseinfo.bookdetailId
                this.dataForm.commentId = data.baseinfo.commentId
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
              url: this.$http.adornUrl(`/product/baseinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'picture': this.dataForm.picture,
                'typeId': this.dataForm.typeId,
                'name': this.dataForm.name,
                'fname': this.dataForm.fname,
                'introduce': this.dataForm.introduce,
                'author': this.dataForm.author,
                'score': this.dataForm.score,
                'priceDj': this.dataForm.priceDj,
                'priceSj': this.dataForm.priceSj,
                'priceYf': this.dataForm.priceYf,
                'insale': this.dataForm.insale,
                'keyword': this.dataForm.keyword,
                'stock': this.dataForm.stock,
                'integral': this.dataForm.integral,
                'remarks': this.dataForm.remarks,
                'publishId': this.dataForm.publishId,
                'bookdetailId': this.dataForm.bookdetailId,
                'commentId': this.dataForm.commentId
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
