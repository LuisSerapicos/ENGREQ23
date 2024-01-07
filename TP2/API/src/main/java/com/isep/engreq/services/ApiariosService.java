package com.isep.engreq.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.isep.engreq.models.Apiario;
import com.isep.engreq.models.Apicultor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ApiariosService {


    public Apicultor getApicultor(String apicultorId) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("apicultores").document(apicultorId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Apicultor apicultor;
        if (document.exists()) {
            apicultor = document.toObject(Apicultor.class);
            return apicultor;
        } else {
            return null;
        }
    }

    public List<Apiario> getAllApiarios() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference apicultoresCollection = dbFirestore.collection("apicultores");

        List<Apiario> filteredApiarios = new ArrayList<>();

        ApiFuture<QuerySnapshot> apicultoresQuery = apicultoresCollection.get();
        QuerySnapshot apicultoresQuerySnapshot = apicultoresQuery.get();

        for (QueryDocumentSnapshot apicultorDocument : apicultoresQuerySnapshot) {
            CollectionReference apiariosCollection = apicultorDocument.getReference().collection("apiarios");
            ApiFuture<QuerySnapshot> apiariosQuery = apiariosCollection.get();
            QuerySnapshot apiariosQuerySnapshot = apiariosQuery.get();

            for (QueryDocumentSnapshot apiarioDocument : apiariosQuerySnapshot) {
                String apiarioId = apiarioDocument.getId();
                Apiario apiario = apiarioDocument.toObject(Apiario.class);

                if (apiario.getStatus_movimentation() == 0) {
                    apiario.setApiarioId(apiarioDocument.getString("name"));
                    filteredApiarios.add(apiario);
                }
            }
        }

        return filteredApiarios;
    }

    public ResponseEntity<String> approveMovimentation(String id, Map<String, Integer> requestBody)
            throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference apicultoresCollection = dbFirestore.collection("apicultores");

        ApiFuture<QuerySnapshot> apicultoresFuture = apicultoresCollection.get();
        QuerySnapshot apicultoresSnapshot = apicultoresFuture.get();

        for (QueryDocumentSnapshot apicultorDocument : apicultoresSnapshot) {
            CollectionReference apiariosCollection = apicultorDocument.getReference().collection("apiarios");
            DocumentReference apiarioDocumentReference = apiariosCollection.document(id);

            ApiFuture<DocumentSnapshot> apiarioFuture = apiarioDocumentReference.get();
            DocumentSnapshot apiarioDocument = apiarioFuture.get();

            if (apiarioDocument.exists()) {
                // Update status_movimentation
                apiarioDocumentReference.update("status_movimentation", requestBody.get("status"));

                // Record changes in historico_transumancias
                recordHistoricoTransumancias(id, apiarioDocumentReference, requestBody);

                return ResponseEntity.ok("Movimentation approved successfully.");
            }
        }

        return ResponseEntity.notFound().build();
    }



    public List<Apiario> getNewApiarios() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference apicultoresCollection = dbFirestore.collection("apicultores");

        List<Apiario> newApiarios = new ArrayList<>();

        ApiFuture<QuerySnapshot> apicultoresQuery = apicultoresCollection.get();
        QuerySnapshot apicultoresQuerySnapshot = apicultoresQuery.get();

        for (QueryDocumentSnapshot apicultorDocument : apicultoresQuerySnapshot) {
            CollectionReference apiariosCollection = apicultorDocument.getReference().collection("apiarios");

            ApiFuture<QuerySnapshot> apiariosQuery = apiariosCollection.get();
            QuerySnapshot apiariosQuerySnapshot = apiariosQuery.get();

            for (QueryDocumentSnapshot apiarioDocument : apiariosQuerySnapshot) {
                Integer statusAuthorization = apiarioDocument.getLong("status_authorization").intValue();

                if (statusAuthorization == 0) {
                    Apiario apiario = apiarioDocument.toObject(Apiario.class);
                    apiario.setApiarioId(apiarioDocument.getString("name"));
                    newApiarios.add(apiario);
                }
            }
        }

        return newApiarios;
    }

    public ResponseEntity<String> approveRegistration(String id, Map<String, Integer> requestBody)
            throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference apicultoresCollection = dbFirestore.collection("apicultores");

        ApiFuture<QuerySnapshot> apicultoresFuture = apicultoresCollection.get();
        QuerySnapshot apicultoresSnapshot = apicultoresFuture.get();

        for (QueryDocumentSnapshot apicultorDocument : apicultoresSnapshot) {
            CollectionReference apiariosCollection = apicultorDocument.getReference().collection("apiarios");
            DocumentReference apiarioDocumentReference = apiariosCollection.document(id);

            ApiFuture<DocumentSnapshot> apiarioFuture = apiarioDocumentReference.get();
            DocumentSnapshot apiarioDocument = apiarioFuture.get();

            if (apiarioDocument.exists()) {
                // Update the status_authorization field with the provided value
                apiarioDocumentReference.update("status_authorization", requestBody.get("status"));

                CollectionReference historicoTransumanciaCollection = dbFirestore.collection("historico_apiarios");

                CollectionReference apiariosCollection2 = apicultorDocument.getReference().collection("apiarios");
                DocumentReference apiarioDocumentReference2 = apiariosCollection2.document(id);

                ApiFuture<DocumentSnapshot> apiarioFuture2 = apiarioDocumentReference2.get();
                DocumentSnapshot apiarioDocument2 = apiarioFuture2.get();

                Map<String, Object> data = new HashMap<>();
                data.put("Assunto: ", "Autorização de apiário");
                data.put("Atualizado a: ", apiarioDocument2.getUpdateTime());
                data.put("Por: ", apiarioDocument2.getData().get("apicultorId"));
                data.put("Apiário: ", apiarioDocument2.getData().get("name"));
                data.put("Latitude: ", apiarioDocument2.getData().get("latitude"));
                data.put("Longitude: ", apiarioDocument2.getData().get("longitude"));
                data.put("Localização: ", apiarioDocument2.getData().get("location"));

                String status_auth = apiarioDocument2.getData().get("status_authorization").toString();

                if (Objects.equals(status_auth, "0"))
                    data.put("Estado: ", "Pendente");
                else if (Objects.equals(status_auth, "1"))
                    data.put("Estado: ", "Aprovado");
                else if (Objects.equals(status_auth, "2"))
                    data.put("Estado: ", "Rejeitado");

                historicoTransumanciaCollection.add(data);

                return ResponseEntity.ok("Registration approved successfully.");
            }
        }

        // If the loop completes and no matching apiario is found
        return ResponseEntity.notFound().build();
    }




    private void recordHistoricoTransumancias(String id, DocumentReference apiarioDocumentReference,
                                              Map<String, Integer> requestBody) throws ExecutionException, InterruptedException {
        // Retrieve information about the apiario
        ApiFuture<DocumentSnapshot> apiarioFuture = apiarioDocumentReference.get();
        DocumentSnapshot apiarioDocument = apiarioFuture.get();

        if (apiarioDocument.exists()) {
            // Extract relevant information
            String apicultorId = apiarioDocument.getString("apicultorId");
            double latitude = apiarioDocument.getDouble("latitude");
            double longitude = apiarioDocument.getDouble("longitude");
            Timestamp pedido = Timestamp.now();
            int status = requestBody.get("status");

            // Create a new document in historico_transumancias
            Firestore dbFirestore = FirestoreClient.getFirestore();
            CollectionReference historicoTransumanciasCollection = dbFirestore.collection("historico_transumancias");
            Map<String, Object> historicoData = new HashMap<>();
            historicoData.put("apiarioId", id);
            historicoData.put("apicultorId", apicultorId);
            historicoData.put("latitude", latitude);
            historicoData.put("longitude", longitude);
            historicoData.put("pedido", pedido);
            historicoData.put("status", status);

            historicoTransumanciasCollection.add(historicoData);
        }
    }



    public List<Map<String, Object>> getInspecaoForUser(String apicultorId)
            throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference apiariosCollection = dbFirestore.collection("apicultores").document(apicultorId).collection("apiarios");

        List<Map<String, Object>> userInspecaoList = new ArrayList<>();

        ApiFuture<QuerySnapshot> apiariosQuery = apiariosCollection.get();
        QuerySnapshot apiariosQuerySnapshot = apiariosQuery.get();

        for (QueryDocumentSnapshot apiarioDocument : apiariosQuerySnapshot) {

            CollectionReference inspecaoCollection = apiarioDocument.getReference().collection("inspecao");
            ApiFuture<QuerySnapshot> inspecaoQuery = inspecaoCollection.get();
            QuerySnapshot inspecaoQuerySnapshot = inspecaoQuery.get();

            for (QueryDocumentSnapshot inspecaoDocument : inspecaoQuerySnapshot) {
                // Convert each document to a Map and add it to the list
                userInspecaoList.add(inspecaoDocument.getData());
            }
        }

        return userInspecaoList;
    }





}
