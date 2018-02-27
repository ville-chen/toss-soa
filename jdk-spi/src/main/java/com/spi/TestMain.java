package com.spi;

import java.util.ServiceLoader;

/**
 * Created by ville on 2018/2/27.
 *
 * jdk spi的设计目标：
 * 面向对象的设计里，模块之间是基于接口编程，模块之间不能对实现类进行硬编码。
 * 一旦代码里涉及具体的实现类，就违反了可拔插的原则。如果需要替换一种实现，就需要修改代码。
 * 为了实现在模块装配的时候，不在模块里面写死代码，就需要一种服务发现机制。
 * jdk Spi就提供了这样的一种为接口寻找服务实现的机制，将装配的控制权移交到了代码之外。
 *
 * jdk spi的具体实现如下：
 * 当服务的提供者，提供了一个接口的多种实现时，会在jar包的META-INF/service/目录下，创建该接口的同名文件。
 * 该文件里面的内容就是该服务接口的具体实现类的名称。
 * 当外部加载这个模块的时候，就能通过该jar包META-INF/service/下的配置文件，得到具体的实现类名，并加载实例化，完成装配。
 *
 * dubbo不采用jdk spi的原因：
 *     1.jdk的spi会一次实例化扩展点中的所有实现，如果扩展实现很多初始化会很耗时。
 *     如果初始化了但是没有用上，也会浪费资源。dubbo spi则可以通过name的方式进行控制。
 *     2.增加了对扩展点Ioc和AOP的支持，一个扩展点可以直接setter注入其它扩展点。
 *
 * dubbo spi的实现：
 * spi文件的存储路径：META-INF\dubbo\internal\包名+接口名
 * spi文件格式：扩展名=具体类名 name=com.InterImpl,通过name来控制实现类的加载。
 *
 */
public class TestMain {

    public static void main(String[] args) {
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);

        for (Command cmd : serviceLoader) {
            cmd.execute();
        }
    }
}
