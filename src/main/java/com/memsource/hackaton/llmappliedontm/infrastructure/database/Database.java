package com.memsource.hackaton.llmappliedontm.infrastructure.database;

import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
@Data
public class Database {

    private final Map<String, Dataset> datasets = createDatasets();

    private Map<String, Dataset> createDatasets() {
        Map<String, Dataset> result = new HashMap<>();
        addEnglishToSlovakDataset(result);
        addEnglishToPolishDataset(result);
        addEnglishToGermanDataset(result);
        addEnglishToSlovakDatasetWithLongSentences(result);

        return result;
    }

    private static void addEnglishToSlovakDataset(Map<String, Dataset> result) {
        result.put("dataset1", Dataset.builder()
                .id("dataset1")
                .name("English to Slovak")
                .sourceLanguage("en")
                .targetLanguage("sk")
                .segments(
                        List.of(
                                Dataset.Segment.builder()
                                        .source("He is a doctor.")
                                        .target("On je doktor.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("She is an actress.")
                                        .target("Je herečka.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("His mother and father live abroad.")
                                        .target("Jeho matka a otec žijú v zahraničí.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("He works as a software developer.")
                                        .target("Pracuje ako softwarový vývojár.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Her father is afraid of climate change.")
                                        .target("Jej otec sa bojí klimatických zmien.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("His brother is lost after night out.")
                                        .target("Jeho brat je po noci stratený.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("I will ask her about her innocence.")
                                        .target("Spýtam sa jej na jej nevinu.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("What does he even think he is?!")
                                        .target("Čo si vôbec myslí, že je?!")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Her malaise has started to appear after her vacation.")
                                        .target("Po dovolenke sa jej začala objavovať malátnosť.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("He has never been drafted by any NHL team.")
                                        .target("Nikdy nebol draftovaný žiadnym tímom NHL.")
                                        .build()
                        )
                )
                .build());
    }

    private static void addEnglishToPolishDataset(Map<String, Dataset> result) {
        result.put("dataset2", Dataset.builder()
                .id("dataset2")
                .name("English to Polish")
                .sourceLanguage("en")
                .targetLanguage("pl")
                .segments(
                        List.of(
                                Dataset.Segment.builder()
                                        .source("He is a doctor.")
                                        .target("On jest lekarzem.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("She is an actress.")
                                        .target("Ona jest aktorką.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("His mother and father live abroad.")
                                        .target("Jego matka i ojciec mieszkają za granicą.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("He works as a software developer.")
                                        .target("On pracuje jako programista.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Her father is afraid of climate change.")
                                        .target("Jej ojciec boi się zmian klimatycznych.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("His brother is lost after night out.")
                                        .target("Jego brat zgubił się po nocy poza domem.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("I will ask her about her innocence.")
                                        .target("Zapytam ją o jej niewinność.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("What does he even think he is?!")
                                        .target("Co on sobie w ogóle myśli?")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Her malaise has started to appear after her vacation.")
                                        .target("Jej złe samopoczucie zaczęło się pojawiać po jej wakacjach.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("He has never been drafted by any NHL team.")
                                        .target("On nigdy nie został wybrany przez żadną drużynę NHL.")
                                        .build()
                        )
                )
                .build());
    }

    private static void addEnglishToGermanDataset(Map<String, Dataset> result) {
        result.put("dataset3", Dataset.builder()
                .id("dataset3")
                .name("English to German")
                .sourceLanguage("en")
                .targetLanguage("de")
                .segments(
                        List.of(
                                Dataset.Segment.builder()
                                        .source("He is a doctor.")
                                        .target("Er ist ein Arzt.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("She is an actress.")
                                        .target("Sie ist eine Schauspielerin..")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("His mother and father live abroad.")
                                        .target("Seine Mutter und sein Vater leben im Ausland.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("He works as a software developer.")
                                        .target("Er arbeitet als Softwareentwickler.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Her father is afraid of climate change.")
                                        .target("Ihr Vater hat Angst vor dem Klimawandel.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("His brother is lost after night out.")
                                        .target("Sein Bruder ist nach einer Nacht verloren.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("I will ask her about her innocence.")
                                        .target("Ich werde sie nach ihrer Unschuld fragen.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("What does he even think he is?!")
                                        .target("Für was hält er sich überhaupt?!")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Her malaise has started to appear after her vacation.")
                                        .target("Nach ihrem Urlaub macht sich ihr Unwohlsein bemerkbar.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("He has never been drafted by any NHL team.")
                                        .target("Er wurde noch nie von einem NHL-Team gedraftet.")
                                        .build()
                        )
                )
                .build());
    }

    private static void addEnglishToSlovakDatasetWithLongSentences(Map<String, Dataset> result) {
        result.put("dataset4", Dataset.builder()
                .id("dataset4")
                .name("Propagate changes")
                .sourceLanguage("sk")
                .targetLanguage("en")
                .segments(
                        List.of(
                                Dataset.Segment.builder()
                                        .source("Programátorský tím sa venoval práci na LLM a vyriešil úlohu propagácie nejakej konverzie textu do ďalšieho textu.")
                                        .target("The programming team worked on the LLM and solved the task of promoting some text-to-text conversion.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Pre podobné a komplikovanejšie úlohy bude potrebné rozšíriť programátorský tím o špecialistov na AI/ML, ktorí majú prehľad o rôznych technológiách, vrátane LLM.")
                                        .target("For similar and more complicated tasks, it will be necessary to expand the programming team with AI/ML specialists who have an overview of various technologies, including LLM.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Veta nesúvisiaca s programovaním")
                                        .target("A sentence not related to programming")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Firma zamestnáva ľudí zaoberajúcimi sa rôznymi oblasťami")
                                        .target("The company employs people dealing with various fields")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Včera bolo slnečno, predvčerom prešla ponad jizerky búrka")
                                        .target("Yesterday was sunny, the day before yesterday a storm passed over the Jizera mountains")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Najbližší sprint nám v tíme budú chýbať dvaja programátori")
                                        .target("The next sprint, we will be missing two programmers in the team")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Už neviem, čo by som sem napísal")
                                        .target("I don't know what to write here")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Oblasť LLM sa začala intenzívne študovať")
                                        .target("The field of LLM has begun to be intensively studied")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Výrobcovia nábytku majú v niečom podobnú prácu dodávateľom software.")
                                        .target("Furniture manufacturers have a somewhat similar job to software suppliers.")
                                        .build(),
                                Dataset.Segment.builder()
                                        .source("Najprv zozbierajú požiadavky zákazníka, potom ich pretavia do prezentovaného modelu a nakoniec pripravia výrobok.")
                                        .target("First, they collect the customer's requirements, then transform them into the presented model, and finally prepare the product.")
                                        .build()
                        )
                )
                .build());
    }
}
