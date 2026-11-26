# PROMPT GOOGLE AI STUDIO (MODE BUILD) — AgriBun

> Tempel seluruh isi file ini ke kolom prompt Google AI Studio → Build. Bangun aplikasi lengkap sekaligus, lalu iterasi per bagian bila perlu.

---

## 1. PERAN

Anda adalah Senior Mobile/Frontend Engineer. Bangun aplikasi **AgriBun: Aplikasi Belajar Agribisnis Perkebunan SMK**, media belajar mandiri untuk murid kelas X (Fase E) SMK Agribisnis Tanaman, mata pelajaran *Dasar-dasar Agribisnis Tanaman Perkebunan*.

Aplikasi harus berupa **Progressive Web App (PWA) mobile-first** yang dapat dipasang ke layar utama ponsel Android ("Add to Home Screen") dan **berjalan penuh tanpa internet** setelah dipasang. Tampilannya harus terasa seperti aplikasi Android native (bottom navigation, top app bar, kartu, transisi halus, ripple/press feedback, ukuran sentuh minimal 48dp).

## 2. TEKNOLOGI

- React 18 + TypeScript (strict), Vite
- Tailwind CSS (tema kustom, lihat bagian 9)
- React Router (HashRouter agar aman untuk hosting statis dan offline)
- State: React hooks + Context (tanpa library state eksternal)
- Penyimpanan lokal: `localStorage` dibungkus abstraksi `LocalStorageDataSource` (boleh diganti IndexedDB tanpa mengubah layer lain)
- PWA: `manifest.webmanifest` + Service Worker (precache seluruh aset, strategi cache-first untuk aset statis, runtime cache untuk gambar)
- Ikon: `lucide-react`
- Tanpa backend, tanpa login, tanpa API key. Semua konten disimpan di dalam aplikasi (data statis TypeScript).
- Seluruh antarmuka dan konten dalam **Bahasa Indonesia** yang sederhana dan sesuai untuk remaja 15–16 tahun.

## 3. ARSITEKTUR WAJIB: MVVM + CLEAN LAYERING

Terapkan pola **MVVM** dengan aliran data satu arah (*unidirectional data flow*). Pisahkan lapisan secara tegas. Dependensi hanya boleh mengalir: **View → ViewModel → Repository → DataSource → Model**. View dilarang mengakses Repository/DataSource langsung. ViewModel dilarang mengimpor komponen UI.

| Lapisan | Peran | Implementasi |
|---|---|---|
| **Model** | Entitas dan tipe data murni | `src/model/*.ts` (interface/type, tanpa logika UI) |
| **DataSource** | Sumber data mentah | `ContentDataSource` (konten statis), `LocalStorageDataSource` (progres/nilai) |
| **Repository** | Satu pintu data untuk ViewModel; memetakan data mentah ke Model | `src/data/repository/*Repository.ts` (class + interface) |
| **ViewModel** | Logika presentasi, mengelola `UiState`, menerima `UiEvent/Action` dari View | Custom hook `use<Nama>ViewModel()` di `src/viewmodel/` |
| **View** | Komponen UI deklaratif tanpa logika bisnis; hanya membaca `uiState` dan memanggil fungsi ViewModel | `src/view/screens/*` dan `src/view/components/*` |

### Aturan arsitektur
1. Setiap layar punya **satu ViewModel** yang mengekspos `uiState` (objek tunggal immutable) dan fungsi aksi (mis. `onAnswerSelected`, `onSubmitQuiz`).
2. `UiState` memakai pola status eksplisit:
   ```ts
   type UiState<T> =
     | { status: 'loading' }
     | { status: 'success'; data: T }
     | { status: 'error'; message: string }
     | { status: 'empty' };
   ```
3. Repository didefinisikan lewat **interface** (mis. `IProgressRepository`) dan di-*inject* ke ViewModel melalui **Context provider** (`AppContainer` / dependency container sederhana) agar mudah diuji.
4. Tidak ada `localStorage` atau data konten yang diakses langsung dari komponen View.
5. Komponen View harus *stateless* sedapat mungkin (props in, event out). State lokal UI murni (mis. tab aktif, accordion terbuka) boleh di View.
6. Gunakan `useMemo`/`useCallback` secukupnya; hindari re-render yang tidak perlu.
7. Semua fungsi async mengembalikan `Promise` dan dibungkus penanganan error di ViewModel.
8. Pemisahan *pure logic* (penilaian kuis, N-Gain, persentase kemajuan) ke `src/domain/` (use case / utilitas murni) agar bisa diuji unit.

### Struktur folder
```
src/
├─ main.tsx
├─ App.tsx                      # Router + AppContainer provider + layout shell
├─ di/
│  └─ AppContainer.tsx          # Context penyedia repository
├─ model/
│  ├─ Unit.ts                   # Unit, Section, Media
│  ├─ Commodity.ts
│  ├─ GlossaryTerm.ts
│  ├─ CaseStudy.ts
│  ├─ Question.ts               # Question (pilihan ganda, mencocokkan, menyusun urutan)
│  ├─ QuizResult.ts
│  └─ Progress.ts               # UserProfile, UnitProgress, ExamAttempt
├─ data/
│  ├─ source/
│  │  ├─ ContentDataSource.ts   # menyajikan konten statis
│  │  ├─ LocalStorageDataSource.ts
│  │  └─ content/               # units.ts, commodities.ts, glossary.ts, caseStudies.ts, questions.ts
│  └─ repository/
│     ├─ IContentRepository.ts / ContentRepository.ts
│     ├─ IProgressRepository.ts / ProgressRepository.ts
│     └─ IQuizRepository.ts / QuizRepository.ts
├─ domain/
│  ├─ gradeQuiz.ts              # penilaian (0–100) + umpan balik
│  ├─ calculateNGain.ts         # N-Gain = (post - pre) / (100 - pre), kategori
│  ├─ calculateProgress.ts
│  └─ buildReportText.ts        # teks rekap untuk guru
├─ viewmodel/
│  ├─ useOnboardingViewModel.ts
│  ├─ useLearningMapViewModel.ts
│  ├─ useUnitDetailViewModel.ts
│  ├─ useGalleryViewModel.ts
│  ├─ useGlossaryViewModel.ts
│  ├─ useCaseStudyViewModel.ts
│  ├─ useQuizViewModel.ts
│  └─ useReportCardViewModel.ts
├─ view/
│  ├─ navigation/               # BottomNav, routes
│  ├─ screens/                  # OnboardingScreen, LearningMapScreen, UnitDetailScreen,
│  │                            # GalleryScreen, CommodityDetailScreen, GlossaryScreen,
│  │                            # CaseStudyListScreen, CaseStudyScreen, QuizHomeScreen,
│  │                            # QuizScreen, QuizResultScreen, ReportCardScreen, AboutScreen
│  ├─ components/               # TopAppBar, UnitCard, ProgressBar, VideoLinkCard, QuestionCard,
│  │                            # MatchingQuestion, OrderingQuestion, ErrorState, EmptyState,
│  │                            # LoadingSkeleton, OfflineBadge, InstallPrompt
│  └─ theme/                    # warna, tipografi
├─ pwa/
│  ├─ registerSW.ts
│  └─ useOnlineStatus.ts
public/
├─ manifest.webmanifest
├─ sw.js
└─ icons/                       # 192x192 dan 512x512 (maskable)
```

## 4. MODEL DATA (TypeScript)

```ts
export type TP = 1 | 2 | 3 | 4;

export interface Media { type: 'image' | 'infographic' | 'video'; title: string; src?: string; youtubeUrl?: string; caption?: string; }
export interface Section { id: string; heading: string; body: string; media?: Media[]; keyPoints?: string[]; }
export interface Unit { id: string; tp: TP; title: string; summary: string; icon: string; sections: Section[]; quizId: string; }

export interface Commodity { id: string; name: string; latinName: string; emoji: string; description: string; growthRequirements: string[]; propagation: string; productionStages: string[]; postHarvest: string[]; funFact: string; }
export interface GlossaryTerm { id: string; term: string; definition: string; example?: string; }

export type QuestionType = 'multiple-choice' | 'matching' | 'ordering';
export interface Question { id: string; unitId: string; tp: TP; type: QuestionType; prompt: string; options?: string[]; correctIndex?: number; pairs?: { left: string; right: string }[]; correctOrder?: string[]; explanation: string; }
export interface CaseStudy { id: string; unitId: string; title: string; scenario: string; questions: Question[]; }

export interface UserProfile { name: string; className: string; createdAt: string; }
export interface UnitProgress { unitId: string; sectionsRead: string[]; caseStudyDone: boolean; bestScore: number | null; }
export interface ExamAttempt { id: string; kind: 'pretest' | 'unit-quiz' | 'posttest'; unitId?: string; score: number; correct: number; total: number; takenAt: string; }
```

## 5. FITUR (5 MENU UTAMA = BOTTOM NAVIGATION)

Bottom navigation dengan 5 tab: **Peta**, **Materi**, **Galeri**, **Latihan**, **Rapor**.

### Alur pertama kali buka (Onboarding)
- Layar sambutan singkat (logo AgriBun, 3 poin manfaat) lalu form **nama murid** dan **kelas**. Disimpan lokal. Tidak ada akun.
- Tawarkan **Pretest** (opsional namun disarankan, 10 soal campuran TP 1–4) untuk dasar perhitungan N-Gain.
- Tampilkan banner "Pasang aplikasi" (InstallPrompt) memakai event `beforeinstallprompt`.

### Fitur 1 — Peta Belajar (`LearningMapScreen`)
- Daftar 4 unit sesuai TP 1–4 berbentuk jalur belajar vertikal (kartu bernomor dengan garis penghubung).
- Tiap kartu: judul, ringkasan, ikon, **progress bar** (% bagian terbaca + status studi kasus + skor kuis terbaik), dan tombol "Mulai / Lanjutkan".
- Kartu "Petunjuk Pemakaian" (tampilkan sekali, dapat dibuka ulang).
- Tombol "Lanjutkan belajar" menuju bagian terakhir yang belum selesai.

### Fitur 2 — Materi Visual (`UnitDetailScreen`)
- Isi per unit berupa bagian-bagian singkat (maksimal ±120 kata per bagian): teks, **foto/infografis** (SVG/ilustrasi buatan sendiri di dalam kode atau gambar placeholder `picsum`/Unsplash yang di-cache; sediakan field `src` agar guru dapat mengganti dengan foto kebun sekolah), **poin kunci**, dan **kartu tautan video YouTube** (dibuka di tab baru; tampilkan badge "Perlu internet" bila offline).
- Tombol "Tandai selesai dibaca" per bagian (memperbarui progres).
- Navigasi Sebelumnya/Berikutnya antar bagian, indikator posisi (mis. 2/5).
- Di akhir unit: tombol ke Studi Kasus dan Kuis unit.

### Fitur 3 — Galeri Komoditas dan Glosarium (`GalleryScreen`, `CommodityDetailScreen`, `GlossaryScreen`)
- Galeri 5 komoditas: **kopi, karet, teh, kelapa sawit, kakao** (grid kartu dengan emoji/ilustrasi).
- Detail: syarat tumbuh, cara pembiakan, tahap produksi, pascapasen, fakta menarik.
- Fitur **Bandingkan**: pilih 2 komoditas lalu tampilkan tabel perbandingan berdampingan.
- Glosarium: daftar istilah A–Z dengan **pencarian** (filter real-time), indeks huruf, dan contoh pemakaian. Minimal 25 istilah (mis. agribisnis, hulu, hilir, usahatani, subsistem penunjang, manajemen, planning, organizing, actuating, controlling, iklim, curah hujan, pH tanah, bibit unggul, generatif, vegetatif, stek, okulasi, sambung, cangkok, OPT, gulma, hama, penyakit, pascapanen, sortasi, grading, K3LH, APD).

### Fitur 4 — Studi Kasus dan Latihan (`CaseStudyListScreen`, `CaseStudyScreen`)
- Minimal 4 studi kasus (1 per TP), berupa skenario usaha perkebunan realistis (mis. kebun kopi rakyat dengan hasil panen menurun).
- Jenis soal: pilihan ganda, **mencocokkan** (pasangan kiri-kanan, ketuk untuk menjodohkan), dan **menyusun urutan** (tombol naik/turun atau drag).
- **Umpan balik langsung** setelah menjawab (benar/salah + penjelasan), boleh **diulang** tanpa batas.
- Setelah semua soal tuntas, tandai `caseStudyDone = true`.

### Fitur 5 — Kuis dan Rapor Belajar (`QuizHomeScreen`, `QuizScreen`, `QuizResultScreen`, `ReportCardScreen`)
- Mode kuis: **Pretest**, **Kuis Unit** (4 unit), **Posttest** (soal setara pretest dengan susunan/urutan berbeda).
- Satu soal per layar, indikator nomor, tombol Sebelumnya/Berikutnya, konfirmasi sebelum kirim. Acak urutan soal dan opsi tiap percobaan.
- Skor otomatis 0–100, tampil langsung beserta status **KKTP (≥ 75)** lulus/belum, daftar soal salah + pembahasan, dan tombol "Ulangi".
- **Rapor Belajar:** identitas murid, kemajuan per unit, riwayat nilai (daftar + grafik batang sederhana), skor pretest, skor posttest, **N-Gain** beserta kategori (tinggi ≥ 0,7; sedang 0,3–0,7; rendah < 0,3), dan lencana (badge) sederhana.
- **Kirim rekap ke guru:** tombol yang menghasilkan teks rekap rapi dan membagikannya lewat **Web Share API** (fallback: salin ke clipboard + tautan `https://wa.me/?text=` terkode). Sediakan juga **Unduh CSV** ringkasan nilai.
- Tombol "Hapus semua data saya" dengan dialog konfirmasi.

### Fitur penunjang
- **Offline-first:** badge status online/offline di top app bar; semua fitur inti jalan tanpa internet.
- Layar **Tentang** (nama aplikasi, versi 1.0, pembuat/guru, capaian pembelajaran, tujuan TP 1–4, kredit).
- **Mode gelap** mengikuti sistem, dengan toggle manual di Tentang.
- Aksesibilitas: label ARIA, kontras warna memadai (WCAG AA), ukuran font minimal 16px, mendukung pembesaran teks.

## 6. KONTEN YANG HARUS DIHASILKAN (Bahasa Indonesia, akurat secara ilmiah)

Buat konten lengkap, bukan placeholder "lorem ipsum".

**Unit 1 — Sistem dan Manajemen Agribisnis (TP 1):** pengertian agribisnis; empat subsistem (hulu/sarana produksi, usahatani/budidaya, hilir/pengolahan dan pemasaran, penunjang); fungsi manajemen (perencanaan, pengorganisasian, pelaksanaan, pengendalian) pada usaha perkebunan; contoh alur kopi dari bibit hingga kedai kopi.

**Unit 2 — Faktor yang Memengaruhi Produksi (TP 2):** tanah (jenis, pH, kesuburan, drainase), iklim (curah hujan, suhu, ketinggian, cahaya), genetik/bibit unggul (varietas, klon), makhluk hidup pengganggu (hama, penyakit, gulma) beserta contoh pada komoditas.

**Unit 3 — Pembiakan Tanaman (TP 3):** generatif (biji) vs vegetatif (stek, cangkok, okulasi/sambung, kultur jaringan); kelebihan-kekurangan; tabel pilihan cara pembiakan per komoditas (mis. kelapa sawit dengan biji; karet dengan okulasi; teh dengan stek; kakao dengan biji/sambung; kopi dengan biji/sambung).

**Unit 4 — Produksi dan Pascapanen dengan K3LH (TP 4):** persiapan lahan dan tanam, pemeliharaan (penyiraman, pemupukan, penyiangan, pengendalian OPT), panen (kriteria matang), pascapanen (sortasi, pengolahan primer, pengemasan, penyimpanan), penerapan K3LH (APD, bahaya kerja, limbah, kelestarian lingkungan).

**Bank soal:** minimal **8 soal per unit** (campuran 3 jenis soal) = 32 soal kuis unit, ditambah **10 soal pretest** dan **10 soal posttest** setara. Setiap soal memiliki `explanation`. Setiap studi kasus minimal 4 soal.

> Catatan: Capaian Pembelajaran bersifat ringkasan; guru akan menyesuaikan dengan teks resmi Kepka BSKAP No. 033/H/KR/2022. Cantumkan CP dan TP di layar Tentang sebagai teks yang mudah diubah di satu berkas konten.

## 7. PENYIMPANAN LOKAL (kunci `localStorage`, diawali `agribun.v1.`)

`profile`, `progress` (map unitId → UnitProgress), `attempts` (array ExamAttempt), `settings` (tema, onboardingDone, petunjukDilihat). Sertakan **versi skema** dan fungsi migrasi sederhana. Semua baca/tulis dibungkus `try/catch` (tangani kuota penuh dan mode privat).

## 8. PWA

- `manifest.webmanifest`: `name: "AgriBun"`, `short_name: "AgriBun"`, `display: "standalone"`, `orientation: "portrait"`, `start_url: "./"`, `theme_color` hijau, `background_color` putih, ikon 192 dan 512 (termasuk maskable).
- Service Worker: precache shell + seluruh aset hasil build, fallback ke `index.html` untuk navigasi, pembaruan versi dengan *skip waiting* dan banner "Versi baru tersedia — Muat ulang".
- Aplikasi harus lolos kriteria *installable* dan tetap membuka semua layar saat mode pesawat.

## 9. DESAIN (Material Design 3 bernuansa Android)

- Tema agraris: hijau daun (primer `#2E7D32`), hijau muda (`#A5D6A7`), cokelat tanah (sekunder `#6D4C41`), kuning padi (aksen `#F9A825`), latar `#F6FBF4`; varian gelap yang selaras.
- Tipografi: Inter atau Poppins (fallback sistem). Sudut membulat 16–24px, elevasi lembut, ikon konsisten.
- Top App Bar dengan judul layar + tombol kembali; **Bottom Navigation** 5 tab dengan indikator aktif; kartu, *chip*, *snackbar* untuk notifikasi singkat, *dialog* konfirmasi.
- Animasi transisi antarlayar halus (≤ 250 ms), skeleton saat memuat, status kosong dan error yang ramah.
- Layout fokus ponsel (lebar 360–412px), tetap rapi di tablet (konten dipusatkan, maks 720px).
- Hormati *safe area* (notch) dan hindari konten tertutup bottom nav.

## 10. KUALITAS DAN BATASAN

- TypeScript strict, tanpa `any`; hindari *prop drilling* berlebihan; penamaan jelas; komentar ringkas pada logika penting.
- Tidak ada permintaan jaringan selain tautan YouTube yang dibuka pengguna dan font (sediakan fallback bila offline).
- Jangan menyimpan atau mengirim data pribadi ke server mana pun. Semua data tetap di perangkat murid.
- Tambahkan unit test sederhana (Vitest) untuk `gradeQuiz`, `calculateNGain`, dan `calculateProgress`.
- Aplikasi harus bisa di-*build* tanpa galat dan tanpa peringatan TypeScript.

## 11. KRITERIA PENERIMAAN (periksa sebelum selesai)

- [ ] Struktur folder dan pemisahan lapisan MVVM sesuai bagian 3; tidak ada View yang memanggil Repository/DataSource langsung.
- [ ] Setiap layar memiliki ViewModel dengan `UiState` (loading/success/error/empty).
- [ ] 5 menu utama berfungsi: Peta, Materi, Galeri+Glosarium, Studi Kasus+Latihan, Kuis+Rapor.
- [ ] 4 unit (TP 1–4), 5 komoditas, ≥ 25 istilah glosarium, ≥ 4 studi kasus, ≥ 32 soal kuis + pretest/posttest 10 soal.
- [ ] Skor otomatis, KKTP 75, N-Gain terhitung dan terkategori, rapor tampil.
- [ ] Kirim rekap ke guru (Web Share / salin / CSV) berfungsi.
- [ ] Dapat dipasang di Android dan berjalan penuh secara offline.
- [ ] Data murid hanya tersimpan di perangkat dan dapat dihapus.
- [ ] Tampilan responsif, aksesibel, mendukung mode gelap.

## 12. LANGKAH KERJA YANG DIHARAPKAN

1. Tampilkan ringkasan rencana arsitektur singkat (pohon folder + alur data).
2. Hasilkan seluruh kode (Model → DataSource → Repository → Domain → ViewModel → View → PWA).
3. Jalankan, periksa tiap kriteria penerimaan, dan perbaiki sendiri bila ada yang belum terpenuhi.
4. Akhiri dengan daftar singkat cara menguji offline dan cara memasang di Android.

---
*Dasar rancangan: LK-1 Idea Card "AgriBun" (4 November 2026).*
