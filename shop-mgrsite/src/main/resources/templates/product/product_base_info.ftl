<div style="padding-bottom: 10px;padding-left: 10px;padding-right: 10px;padding-top: 10px;">
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label>商品名称</label> <input class="form-control"
					name="product.name">
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
				<label>商品编号</label> <input class="form-control"
					name="product.code">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label>所属品牌</label> <select class="form-control"
					name="product.brand.id">
				<option>请选择品牌</option>
				<#list brandList as brand >
                    <option value="${brand.id}">${brand.name}</option>
				</#list>
				</select>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
                <label>所属分类</label> <select class="form-control"
					id="catalogId" name="product.catalog.id" onchange="changeCatalog(this)">
                <option>请选择分类</option>
				<#list catalogList as catalog>
                	<option value="${catalog.id}">${catalog.name}</option>
				</#list>
				</select>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label>市场售价</label> <input class="form-control"
					name="product.marketPrice">
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
				<label>基础售价</label> <input class="form-control"
					name="product.basePrice">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="form-group">
				<label>商品关键字</label> <input class="form-control"
					placeholder="以逗号分隔" name="product.keyword">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="form-group">
				<label>商品标签</label>
				<textarea class="form-control" rows="3" placeholder="以逗号分隔"
					name="product.label"></textarea>
			</div>
		</div>
	</div>
</div>