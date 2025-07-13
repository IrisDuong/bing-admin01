package com.bh.admin.warehouse.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.bh.admin.util.AppConstants;
import com.bh.admin.util.BaseAuditing;

@Entity
@Table(name = "WH_PRODUCT_CATEGORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory extends BaseAuditing<String>{

	@Id
	@Column(name = "product_category_code", length = 14)
	@GeneratedValue(generator = "pk_generator")
	@GenericGenerator(name = "pk_generator", parameters = @Parameter(name = "prefix", value = "PC-"), strategy = AppConstants.PK_GENERATOR_LOCATION)
	private String productCategoryCode;
	
	@Column(length = 4000, nullable = false)
	private String productCategoryName;
	
	@Column(length = 3, nullable = false)
	private String status;
	

	@Column(name = "tree_level")
	private int treeLevel;

	@Column(name = "order_seq")
	private int orderSeq;

	@Column(name = "parent_category_id")
	private Integer parentCategoryId;

	@Column(name = "is_sub_category")
	private boolean isSubCategory;
	
	public ProductCategory(String productCategoryCode) {
		super();
		this.productCategoryCode = productCategoryCode;
	}

	public ProductCategory(
			String productCategoryCode, String productCategoryName, String status, int treeLevel, int orderSeq,
			Integer parentCategoryId, boolean isSubCategory,String createdBy, LocalDateTime createdAt, String updatedBy, LocalDateTime updatedAt) {
		super(createdBy, createdAt, updatedBy, updatedAt);
		this.productCategoryCode = productCategoryCode;
		this.productCategoryName = productCategoryName;
		this.status = status;
		this.treeLevel = treeLevel;
		this.orderSeq = orderSeq;
		this.parentCategoryId = parentCategoryId;
		this.isSubCategory = isSubCategory;
	}
	
	
}
