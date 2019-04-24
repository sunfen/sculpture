package cn.sf.sculpture.document.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.sculpture.document.domain.DocumentVO;
import cn.sf.sculpture.document.domain.entity.Document;
import cn.sf.sculpture.document.repository.DocumentRepository;
import cn.sf.sculpture.document.service.DocumentService;
import cn.sf.sculpture.document.util.DocumentConvertUtil;
import cn.sf.sculpture.document.util.UpPictures;
import net.coobird.thumbnailator.Thumbnails;


/**
 * @author ztw
 * @date 2018/12/29
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Value("${sculpture.url}")
    private String Base_Path;
    
    @Value("${sculpture.file.upload-path}")
    private String fahterPath;
    
    @Autowired
    private DocumentConvertUtil convertUtil;
    
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public DocumentVO insert(InputStream inputStream,
        String fileName, Long objectId) throws IOException {
        
        Assert.notNull(fileName, "fileName is null");
        
        return this.inserts(inputStream, fileName, objectId);

    }
    
    private DocumentVO inserts(final InputStream inputStream, final String fileName, Long objectId) throws IOException {
        Path path = Paths.get(this.fahterPath);
        
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        path = Paths.get(path.toString());
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
       
        final Document document = new Document();
        final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        final String type = '.' + fileName.substring(fileName.lastIndexOf('.') + 1);
        
        document.setName(fileName.replaceAll(" ", ""));
     
        document.setObjectId(objectId);
     
        final Path createFile = Paths.get(path.toString(), uuid + type);
       
        Files.copy(inputStream, createFile);
        
        document.setPathName(createFile.toUri().toString());
       
        if (UpPictures.isImage(fileName)) {
            
            final Path minPath = Files.createFile(Paths.get(path.toString(), "min" + uuid + type));
            document.setMinPath(minPath.toUri().toString());
         
            try {
                final InputStream source = Files.newInputStream(createFile);
                
                final OutputStream outputStream = Files.newOutputStream(minPath);
                
                Thumbnails.of(source).size(300, 200).toOutputStream(outputStream);
                outputStream.flush();
                
            } catch (Exception e) {
                Files.deleteIfExists(minPath);
                Files.deleteIfExists(createFile);
                e.printStackTrace();
                throw new IOException();
            }
        }
        
        return this.convertUtil.convertDocument(this.documentRepository.save(document));
    }
    
    
    @Override
    @Transactional
    public void deleteDocumentByObjectId(Long objectId) throws IOException {
        List<Document> document = documentRepository.findByObjectId(objectId);
        for(Document doc : document) {
            if (doc != null) {
               this.deleteDoucumentByPath(doc.getPathName(), doc.getMinPath());
           }
            documentRepository.delete(doc);
        }
    }


    public void deleteDoucumentByPath(String pathStr, String minPathStr) throws IOException {
        final Path path = Paths.get(this.fahterPath);
        final Set<String> attrViews = path.getFileSystem().supportedFileAttributeViews();

        if (pathStr != null && !pathStr.isEmpty()) {
            if (!attrViews.contains("posix")) {
                Files.deleteIfExists(Paths.get(pathStr.replace("file:///", "")));
                if (minPathStr != null && !minPathStr.isEmpty()) {
                    Files.deleteIfExists(Paths.get(pathStr.replace("file:///", "")));
                }
            } else {
                Files.deleteIfExists(Paths.get(pathStr.replace("file://", "")));
                if (minPathStr != null && !minPathStr.isEmpty()) {
                    Files.deleteIfExists(Paths.get(pathStr.replace("file://", "")));
                }
            }
        }
    }



    
    

    /* (non-Javadoc)
     * @see com.ysxx.vias.common.service.DocumentService#allDocumentByObjectId(java.lang.String)
     */
    @Override
    public List<DocumentVO> allDocumentByObjectId(Long objectId) {
         final List<Document> results = documentRepository.findByObjectId(objectId);
         return convertUtil.convertDocument(results);
    }
    
    
    
    

    /** (non-Javadoc)
     * @see com.ysxx.vias.common.service.DocumentService#deleteDocumentById(java.lang.String)
     */
    @Override
    @Transactional
    public void deleteDocumentById(Long id) {
        documentRepository.deleteById(id);
    }

    
    
    
    
    /* (non-Javadoc)
     * @see cn.sf.sculpture.document.service.DocumentService#deleteDocumentByIds(java.util.List)
     */
    @Override
    @Transactional
    public void deleteDocumentByIds(List<Long> removeImages) throws IOException {
        List<Document> documents = documentRepository.findAllById(removeImages);
        for(Document doc : documents) {
            if (doc != null) {
               this.deleteDoucumentByPath(doc.getPathName(), doc.getMinPath());
           }
            documentRepository.delete(doc);
        }
         
    }

    
}
