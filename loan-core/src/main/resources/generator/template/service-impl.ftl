package ${basePackage}.service.impl;

import com.hwc.mybatis.core.AbstractService;
import ${basePackage}.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ${date}.
 */
@Service
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}Mapper, ${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    private static final Logger logger = LoggerFactory.getLogger(${modelNameUpperCamel}ServiceImpl.class);


}
