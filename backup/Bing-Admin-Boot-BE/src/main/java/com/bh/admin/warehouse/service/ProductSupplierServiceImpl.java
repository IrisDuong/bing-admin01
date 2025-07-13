package com.bh.admin.warehouse.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.admin.util.exception.NoContentException;
import com.bh.admin.warehouse.dao.ProductSupplierDao;
import com.bh.admin.warehouse.dto.ProductSupplierDTO;
import com.bh.admin.warehouse.entity.ProductSupplier;

@Service
@Transactional
public class ProductSupplierServiceImpl implements ProductSupplierService{
	@Autowired
	ProductSupplierDao productSupplierDao;

	@Override
	public List<ProductSupplierDTO> getAllProductSuppliers() {
		// TODO Auto-generated method stub
		List<ProductSupplierDTO> result = productSupplierDao.findAll().stream()
				.map(item-> mapToDTO(item)).collect(Collectors.toList());
		return result;
	}

	@Override
	public List<ProductSupplierDTO> searchProductSuppliers(ProductSupplierDTO param) {
		Specification<ProductSupplier> supplierCode = (root,query,builder)->{
			return !param.getSupplierCode().equals("")
					? builder.equal(root.get("supplierCode"), param.getSupplierCode())
					: builder.isNotNull(root.get("supplierCode"));
					
		};
		
		Specification<ProductSupplier> others = (root,query,builder)->{
			return builder.and(
					builder.like(root.get("supplierName"), "%"+param.getSupplierName()+"%")
					,builder.like(root.get("phoneNo"), "%"+param.getPhoneNo()+"%")
					,builder.like(root.get("address"), "%"+param.getAddress()+"%")
					,builder.like(root.get("email"), "%"+param.getEmail()+"%")
					);
		};
		List<ProductSupplier> result = productSupplierDao.findAll(supplierCode.and(others));
		AtomicInteger rowNo = new AtomicInteger(1);
		List<ProductSupplierDTO> productSuppliers = result.stream()
				.map(supplier-> mapToDTO(supplier)).collect(Collectors.toList());
		productSuppliers.forEach(item-> item.setRowNo(rowNo.getAndIncrement()));
		// TODO Auto-generated method stub
		return productSuppliers;
	}

	@Override
	public void saveProductSupplier(ProductSupplierDTO param) {
		// TODO Auto-generated method stub
		ProductSupplier _param = mapToModel(param);
		try {
			productSupplierDao.save(_param);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public Optional<ProductSupplierDTO>  getSupplierByCode(String supplierCode) {
		// TODO Auto-generated method stub
		ProductSupplier result = productSupplierDao.findById(supplierCode)
				.orElseThrow(()-> new NoContentException("No Supplier with code is "+supplierCode));
		Optional<ProductSupplierDTO> productSupplier = Optional.ofNullable(mapToDTO(result));
		return productSupplier;
	}

	
}
