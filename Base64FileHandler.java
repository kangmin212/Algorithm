import java.io.*;
import java.util.Base64;

public class Base64FileHandler {

    // 파일을 Base64로 시리얼라이즈
    public static String serializeToBase64(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] fileBytes = new byte[(int) file.length()];

        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileBytes);
        }

        return Base64.getEncoder().encodeToString(fileBytes);
    }

    // Base64를 파일로 디시리얼라이즈
    public static void deserializeFromBase64(String base64Data, String outputFilePath) throws IOException {
        byte[] fileBytes = Base64.getDecoder().decode(base64Data);

        try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
            fos.write(fileBytes);
        }
    }

    // 테스트 메인 메서드
    public static void main(String[] args) {
        String inputFilePath = "example.txt"; // 인코딩할 파일 경로
        String outputFilePath = "decoded_example.txt"; // 복원할 파일 경로

        try {
            // 파일을 Base64로 시리얼라이즈
            String base64Data = serializeToBase64(inputFilePath);
            System.out.println("Base64 Encoded Data:");
            System.out.println(base64Data);

            // Base64 데이터를 파일로 디시리얼라이즈
            deserializeFromBase64(base64Data, outputFilePath);
            System.out.println("파일이 " + outputFilePath + "로 복원되었습니다!");

        } catch (IOException e) {
            System.err.println("오류가 발생했습니다: " + e.getMessage());
        }
    }
}

//hi