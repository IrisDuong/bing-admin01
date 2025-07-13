package com.bh.admin.warehouse.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.bh.admin.util.AppConstants;
import com.bh.admin.util.BaseAuditing;

@Entity
@Table(name = "WH_PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseAuditing<String> {



	@Id
	@Column(name = "product_code", length = 20)
	@GeneratedValue(generator = "pk_generator")
	@GenericGenerator(name = "pk_generator", parameters = @Parameter(name = "prefix", value = "PI-"), strategy = AppConstants.PK_GENERATOR_LOCATION)
	private String productCode;

	@Column(name = "product_name", unique = true, nullable = false)
	private String productName;
	
	private String manufacturer;
	private BigDecimal importPrice;
	private long importQty;
	private Long productSttCode;
	private Long productImportSttCode;
	private LocalDateTime importDate;
	private Integer attachFileCode;

	@OneToOne
	@JoinColumn(name = "supplier_code")
	private ProductSupplier productSupplier;

	
	@OneToOne
	@JoinColumn(name = "product_category_code")
	private ProductCategory productCategory;


}
