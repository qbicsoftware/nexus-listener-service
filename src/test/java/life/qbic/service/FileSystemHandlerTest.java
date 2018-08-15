package life.qbic.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.*;

public class FileSystemHandlerTest {

    FileSystemHandler fsh;
    private static final Logger LOG = LogManager.getLogger(FileSystemHandlerTest.class);

    @Before
    public void setUp(){
        //http://media.einfachtierisch.de/thumbnail/600/390/media.einfachtierisch.de/images/2015/12/Katze-streicheln-Jakub-Zak-Shutterstock-305145185.jpg

        Client client = new Client("https://github.com/qbicsoftware/nexus-listener-service/blob/development/src/test/resources/vaccine-designer-portlet-1.0.0-20180802.133341-3.war");
        client.downloadFromURL();

     //   fsh = new FileSystemHandler(client.getTmpFileName(),prop.getProperty("user.home"),client.getFileName()+client.getFileFormat());
        fsh = new FileSystemHandler(System.getProperty("java.io.tmpdir")+"/"+client.getTmpFileName(), System.getProperty("user.home"),client.getFileName()+client.getFileFormat());


    }

    @Test
    public void testFileMoving(){
        File fileOld =  new File(fsh.getTempPath());
        Boolean fileExists = fileOld.exists();
        //LOG.info(fsh.getTempPath()+" "+fsh.getOutPath());

        assertTrue(fileExists);
        fsh.move();

        File fileNew = new File(fsh.getOutPath());
        LOG.info(fileNew.getAbsolutePath());

        assertFalse(fileOld.exists());
        assertTrue(fileNew.exists());

        fileNew.delete();
        assertFalse(fileNew.exists());
    }


}
