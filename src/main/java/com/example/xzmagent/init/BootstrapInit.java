//package com.example.xzmagent.init;
//
//import com.example.xzmagent.rag.LoveAppDocumentLoader;
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.Resource;
//import org.springframework.ai.document.Document;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class BootstrapInit {
//    @Resource
//    private LoveAppDocumentLoader loveAppDocumentLoader;
//
//    @Resource
//    private VectorStore pgVectorVectorStore;
//
//
//    @PostConstruct
//    private void initVectorStore() {
//        List<Document> docs = loveAppDocumentLoader.loadMarkdowns();
//        // ✅ 这里自己做分片，每批不超过10条
//        for (int i = 0; i < docs.size(); i += 10) {
//            int end = Math.min(i + 10, docs.size());
//            pgVectorVectorStore.add(docs.subList(i, end));
//        }
//    }
//}
