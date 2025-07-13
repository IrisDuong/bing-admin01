package com.bh.admin.common.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bh.admin.common.entity.LocaleInput;
import com.bh.admin.common.entity.pk.LocaleInputPk;

public interface LocaleInputDao extends JpaRepository<LocaleInput, LocaleInputPk> {

	public LocaleInput findTopByOrderByLocaleCodeDesc();

	public Optional<LocaleInput> findByLocaleCodeAndLangCode(Long localeCode, String langCode);
}
