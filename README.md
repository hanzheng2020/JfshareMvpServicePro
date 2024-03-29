# JfshareMvpServerPro

#### 项目介绍
聚分享mvp版本 的服务端

#### 软件架构

spring boot版本:2.0.3.RELEASE

application-dev.yml 中是开发环境的配置

application-prod.yml 中是正式环境的配置,这里的配置不要更改

API地址:http://{ip}:{port}/swagger-ui.html

AdminController：后台管理模块

ShopController：商城模块

UserController：个人中心模块

#### 安装教程


#### 使用说明

1. liquibase 使用说明

用于数据库版本管理，启动server时自动执行数据库改动 

具体参考http://www.liquibase.org/documentation/

使用示例：

<!-- 每一次改动创建一个changeSet
		id为文件名加序号，整个项目周期中id不可重复
		author为修改者姓名 -->
    <changeSet id="18-07-19-master-1" author="fengxiang">
    		<!-- 创建表 -->
    		<createTable tableName="demo">
    			<!-- 创建column 
    				type：字段类型
    				autoIncrement：是否自增-->
    			<column name="id" type="int" autoIncrement="true">
    				<!-- 限制属性 -->
            		<constraints primaryKey="true" nullable="false" />
            	</column>
    		</createTable>
    	</changeSet>
    	<!-- 每一次改动创建一个changeSet
		id为文件名加序号，整个项目周期中id不可重复
		author为修改者姓名 -->
    <changeSet id="18-07-19-master-2" author="fengxiang">
    	<!-- 新增column -->
        <addColumn tableName="demo" >
            <column name="status" type="tinyint(1)" remarks="状态" defaultValue="0">
            	<constraints nullable="false"/>
            </column>
        </addColumn>
        <!-- 创建index -->
        <createIndex tableName="demo" indexName="index_status">
        		<column name="status"></column>
        </createIndex>
        <!-- 向表中插入数据 -->
        <insert tableName="demo">
        		<column name="status" value="1"></column>
        </insert>
        <!-- 修改表中数据 -->
        <update tableName="demo">
        		<column name="status" value="2"></column>
        </update>
        <!-- 更改column类型 -->
        <modifyDataType tableName="demo" columnName="status" newDataType="int"/>
        <!-- 删除column -->
        <dropColumn tableName="demo" columnName="status"/>
    </changeSet>
2. MyBatis

使用MybatisMapperGeneral来生成mapper和实体类
	
自动生成的mapper.xml放在resources/mybatis/mappers中
	
自定义的mapper.xml放在resources/mybatis/mappers/manual中
	
3. Spring Cache

@Cacheable(cacheNames = "demo") 设置缓存，用于获取资源的方法上

@CacheEvict(cacheNames = "demo") 删除缓存，用于删除资源的方法上

@CachePut(cacheNames = "demo") 更新缓存，用于更新资源的方法上

详细使用说明可参考https://www.cnblogs.com/fashflying/p/6908028.html


Elastisearch 查看header 数据步骤

#head plugin :
http://39.107.230.91:9100/
打开head地址，输入
http://39.107.230.91:9200
