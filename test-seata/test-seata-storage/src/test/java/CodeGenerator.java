import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wusd
 */
class CodeGenerator {

    static final String PACKAGE = "com.github.alexscari";
    static final String[] INCLUDE_TABLES = {"t_storage"};

    @Test
    void generator() {
        AutoGenerator generator = new AutoGenerator();

        // 1、设置模板引擎
        generator.setTemplateEngine(new FreemarkerTemplateEngine());

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setAuthor("wusd");
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setEntityName("%sEntity");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setEntityName("%sEntity");
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);

        gc.setIdType(IdType.ID_WORKER_STR); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(false);

        generator.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.207.128:3306/seata_test?serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        generator.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PACKAGE);
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        pc.setXml(null);
        generator.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(INCLUDE_TABLES);
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix("t_"); //生成实体时去掉表前缀
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(false); // lombok 模型 @Accessors(chain = true) setter链式操作
        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        generator.setStrategy(strategy);

        // 自定义文件配置 xml位置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // do nothing
            }
        };
        FileOutConfig fileOutConfig = new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String tableName = NamingStrategy.removePrefixAndCamel(tableInfo.getName(), strategy.getTablePrefix());
                tableName = NamingStrategy.capitalFirst(tableName);
                return System.getProperty("user.dir") + "/src/main/resources/mapper/" + tableName + "Mapper.xml";
            }
        };
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        fileOutConfigList.add(fileOutConfig);
        injectionConfig.setFileOutConfigList(fileOutConfigList);
        generator.setCfg(injectionConfig);

        // 不生成原来src目录下的xml
        generator.setTemplate(new TemplateConfig().setXml(null));

        // 6、执行
        generator.execute();
    }
}
