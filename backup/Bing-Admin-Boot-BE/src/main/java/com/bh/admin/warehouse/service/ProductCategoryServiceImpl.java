package com.bh.admin.warehouse.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.bh.admin.warehouse.dao.ProductCategoryDao;
import com.bh.admin.warehouse.dto.ProductCategoryDTO;
import com.bh.admin.warehouse.entity.ProductCategory;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	ProductCategoryDao productCategoryDao;


	@Override
	public void saveProductCategory(ProductCategoryDTO param) {
		// TODO Auto-generated method stub
		ProductCategory _param = mapToModel(param);
		 productCategoryDao.save(_param);
	}

	@Override
	public List<ProductCategoryDTO> searchProductCategories(ProductCategoryDTO param){
		// TODO Auto-generated method stub
		Specification<ProductCategory> byCategoryCode = 
				(root, query, criteriaBuilder)-> criteriaBuilder.equal(root.get("productCategoryCode"), param.getProductCategoryCode());
				
		Specification<ProductCategory> byCategoryName = 
						(root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("productCategoryName"), "%"+param.getProductCategoryName()+"%");
		List<ProductCategory> result = productCategoryDao.findAll(byCategoryCode.or(byCategoryName));
//	 // wait for mapstrcuts
//		AtomicInteger rowNo = new AtomicInteger(1);
//	 List<ProductCategoryDTO> listProductCategories = result.stream()
//			 .map(e-> new ProductCategoryDTO(rowNo.getAndIncrement(), e.getProductCategoryCode(), e.getProductCategoryName()))
//			 .collect(Collectors.toList());
	 List<ProductCategoryDTO> listProductCategories = result.stream()
			 .map(item-> mapToDTO(item))
			 .collect(Collectors.toList());
	 return listProductCategories;
	 
//		List<ProductCategoryDto> listProductCategoriesDto = listProductCategoriesModel.stream()
//				.map(c -> new ProductCategoryDto(c.getProductCategoryCode(), c.getProductCategoryName(),
//						c.getCreatedDate(), c.getUpdatedDate(), null))
//				.collect(Collectors.toList());
//		if (listProductCategoriesDto != null && listProductCategoriesDto.size() > 0) {
//			listProductCategoriesDto.get(0)
//					.setListProductGroupsDto(mapToProductGroupDto(listProductCategoriesModel.stream()
//							.filter(c -> c.getProductCategoryCode()
//									.equals(listProductCategoriesDto.get(0).getProductCategoryCode()))
//							.findFirst().orElse(null).getListProductGroups()));
//		}
//		return listProductCategoriesDto;
	}

	@Override
	public ProductCategoryDTO getProductCategoryById(String id) {
		// TODO Auto-generated method stub
		ProductCategory result = productCategoryDao.findById(id).get();
		return mapToDTO(result);
	}


}
