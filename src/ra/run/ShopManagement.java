package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Scanner;

public class ShopManagement {
    Categories[] arrCategories = new Categories[100];
    Product[] arrProducts = new Product[100];
    int indexCategories = 0;
    int indexProduct = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShopManagement shopManagement = new ShopManagement();
        do {
            System.out.println("************SHOP MENU************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shopManagement.displayCategoriesMenu(scanner, shopManagement);
                    break;
                case 2:
                    shopManagement.displayProductMenu(scanner,shopManagement);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public void displayCategoriesMenu(Scanner scanner, ShopManagement shopManagement) {
        boolean isExit = true;
        do {
            System.out.println("*************CATEGORIES MENU************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin các danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shopManagement.inputListCategories(scanner);
                    break;
                case 2:
                    shopManagement.displayListCategories();
                    break;
                case 3:
                    shopManagement.updateCategories(scanner);
                    break;
                case 4:
                    shopManagement.deleteCategories(scanner);
                    break;
                case 5:
                    shopManagement.updateCategorieStatus(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }

    public void displayProductMenu(Scanner scanner, ShopManagement shopManagement) {
        boolean isExit = true;
        do {
            System.out.println("***************PRODUCT MENU*****************");
            System.out.println("1. Nhập thông tin các sản phẩm \n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Sắp xếp các sản phẩm theo giá\n" +
                    "4. Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5. Xóa sản phẩm theo mã sản phẩm\n" +
                    "6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
                    "8. Thoát\n");
            System.out.println("Nhập lựa chọn của bạn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shopManagement.inputListProduct(scanner);
                    break;
                case 2:
                    shopManagement.displayListProduct();
                    break;
                case 3:
                    shopManagement.sortByPrice(scanner);
                    break;
                case 4:
                    shopManagement.updateProductById(scanner);
                    break;
                case 5:
                    shopManagement.deleteProductById(scanner);
                    break;
                case 6:
                    shopManagement.searchProductByName(scanner);
                    break;
                case 7:
                    shopManagement.searchProductInRange(scanner);
                    break;
                case 8:
                    isExit = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn từ 1-8");
            }

        } while (isExit);
    }


    public void inputListCategories(Scanner scanner) {
        System.out.println("Nhập số danh mục cần nhập thông tin:");
        int numberOfCategories = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfCategories; i++) {
            arrCategories[indexCategories] = new Categories();
            arrCategories[indexCategories].inputData(scanner, arrCategories, indexCategories);
            indexCategories++;
        }
    }

    public void displayListCategories() {
        for (int i = 0; i < indexCategories; i++) {
            arrCategories[i].displayData();
        }
    }

    public void updateCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdate = getIndexById(catalogId);
        if (indexUpdate >= 0) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên danh mục");
                System.out.println("2. Cập nhật mô tả");
                System.out.println("3. Cập nhật trạng thái");
                System.out.println("4. Thoát");
                System.out.println("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        arrCategories[indexUpdate].setCatalogName(scanner.nextLine());
                        break;
                    case 2:
                        arrCategories[indexUpdate].setDescription(scanner.nextLine());
                        break;
                    case 3:
                        arrCategories[indexUpdate].setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-4");
                }
            } while (isExit);
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public int getIndexById(int catalogId) {
        for (int i = 0; i < indexCategories; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }

    public void deleteCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndexById(catalogId);
        if (indexDelete >= 0) {
            //Kiểm tra danh mục có sản phẩm chưa
            boolean isExistProduct = false;
            for (int i = 0; i < indexProduct; i++) {
                if (arrProducts[i].getCatalogId() == catalogId) {
                    isExistProduct = true;
                    break;
                }
            }
            if (isExistProduct) {
                System.err.println("Danh mục đang chứa sản phẩm, không thể xóa");
            } else {
                //Thực hiện xóa
                for (int i = indexDelete + 1; i < indexCategories; i++) {
                    arrCategories[i - 1] = arrCategories[i];
                }
                //Gán phần tử cuối là null
                arrCategories[indexCategories - 1] = null;
                //giảm chỉ số đi 1
                indexCategories--;
            }
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public void updateCategorieStatus(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật trạng thái:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdateStatus = getIndexById(catalogId);
        if (indexUpdateStatus >= 0) {
            arrCategories[indexUpdateStatus].setCatalogStatus(!arrCategories[indexUpdateStatus].isCatalogStatus());
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    //  1. Nhập thông tin các sản phẩm
    public void inputListProduct(Scanner scanner) {
        System.out.println("Nhập số sản phẩm cần nhập thông tin");
        int numberOfProduct = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfProduct; i++) {
            arrProducts[indexProduct] = new Product();
            arrProducts[indexProduct].inputData(scanner, arrProducts, indexProduct, arrCategories, indexCategories);
            indexProduct++;
        }
    }

    //  2. Hiển thị thông tin các sản phẩm
    public void displayListProduct() {
        for (int i = 0; i < indexProduct; i++) {
            arrProducts[i].displayData(arrCategories, indexCategories);
        }
    }

    //  3. Sắp xếp các sản phẩm theo giá
    public void sortByPrice(Scanner scanner) {
        System.out.println("Lựa chọn thứ từ sắp xếp");
        System.out.println("1. Sắp xếp giá tăng dần");
        System.out.println("2. Sắp xếp giá giảm dần");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                for (int i = 0; i < indexProduct; i++) {
                    for (int j = 0; j < indexProduct-i-1; j++) {
                        if (arrProducts[j + 1] == null){
                            break;
                        }
                        if (arrProducts[j].getPrice() > arrProducts[j + 1].getPrice()) {
                            Product temp = arrProducts[j];
                            arrProducts[j] = arrProducts[j + 1];
                            arrProducts[j + 1] = temp;
                        }
                    }
                }
                displayListProduct();
                break;
            case 2:
                for (int i = 0; i < indexProduct; i++) {
                    for (int j = 0; j < indexProduct-i-1; j++) {
                        if (arrProducts[j + 1] == null){
                            break;
                        }
                        if (arrProducts[j].getPrice() < arrProducts[j + 1].getPrice()) {
                            Product temp = arrProducts[j];
                            arrProducts[j] = arrProducts[j + 1];
                            arrProducts[j + 1] = temp;
                        }
                    }
                }
                displayListProduct();
                break;
        }
    }

    //4. Cập nhật thông tin sản phẩm theo mã sản phẩm
    public void updateProductById(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm muốn cập nhật");
        String updateProductId = scanner.nextLine();
        int indexUpdate = getIndexProductById(updateProductId);
        if (indexUpdate >= 0) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên sản phẩm");
                System.out.println("2. Cập nhật giá sản phẩm");
                System.out.println("3. Cập nhật mô tả sản phẩm");
                System.out.println("4. Cập nhật ngày nhập sản phẩm");
                System.out.println("5. Cập nhật danh mục sản phẩm");
                System.out.println("6. Cập nhật trạng thái sản phẩm");
                System.out.println("7. Thoát");
                System.out.println("Nhập lựa chọn của bạn");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        arrProducts[indexUpdate].setProductName(scanner.nextLine());
                        break;
                    case 2:
                        arrProducts[indexUpdate].setPrice(Float.parseFloat(scanner.nextLine()));
                        break;
                    case 3:
                        arrProducts[indexUpdate].setDescription(scanner.nextLine());
                        break;
                    case 4:
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            arrProducts[indexUpdate].setCreated(sdf.parse(scanner.nextLine()));
                        } catch (ParseException e) {
                            System.err.println("Định dạng ngày nhập không đúng, vui lòng nhập lại");
                        } catch (Exception ex) {
                            System.err.println("Có lỗi trong quá trình xử lý, vui lòng nhập lại");
                        }
                        break;
                    case 5:
                        System.out.println("Chọn danh mục của sản phẩm:");
                        for (int i = 0; i < indexCategories; i++) {
                            if (arrCategories[i].isCatalogStatus()) {
                                System.out.printf("%d.%s\n", i + 1, arrCategories[i].getCatalogName());
                            }
                        }
                        System.out.print("Lựa chọn của bạn: ");
                        int updateCatalogId = Integer.parseInt(scanner.nextLine());
                        arrProducts[indexUpdate].setCatalogId(updateCatalogId);
                        break;
                    case 6:
                        int updateStatus = Integer.parseInt(scanner.nextLine());
                        if (updateStatus == 0 || updateStatus == 1 || updateStatus == 2) {
                            arrProducts[indexUpdate].setProductStatus(updateStatus);
                        } else {
                            System.err.println("Trạng thái chỉ nhận giá trị 0,1,2, cập nhật không thành công");
                        }
                        break;
                    case 7:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-7");
                        break;
                }
            } while (isExit);
        }

    }

    //5. Xóa sản phẩm theo mã sản phẩm
    public void deleteProductById(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần xóa");
        String productDeleteId = scanner.nextLine();
        int indexDelete = getIndexProductById(productDeleteId);
        if (indexDelete >= 0){
            for (int i = indexDelete + 1 ; i < indexProduct; i++) {
                arrProducts[i-1] = arrProducts[i];
            }
            arrProducts[indexProduct-1]=null;
            indexProduct--;
        }else {
            System.err.println("Mã danh mục không tồn tại");

        }
    }

    //  6. Tìm kiếm các sản phẩm theo tên sản phẩm
    private void searchProductByName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm cần tìm");
        String searchNameProduct = scanner.nextLine().toLowerCase();
        boolean checkSearch = true;
        for (int i = 0; i < indexProduct; i++) {
            if (arrProducts[i].getProductName().contains(searchNameProduct)){
                System.out.println("Sản phẩm còn tên trùng với tên nhập vào :");
                System.out.println(arrProducts[i].getProductName());
                checkSearch = false;
            }
        }
        if (checkSearch){
            System.err.println("Không có sản phẩm nào trùng tên với giá trị tên đã nhập");
        }
    }


    //    7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)
    public void searchProductInRange(Scanner scanner) {
        System.out.println("Nhập giá trị của giới hạn giá phía dưới");
        float lowerPriceLimit = Float.parseFloat(scanner.nextLine());
        float upperPriceLimit;
        while (true)
        {
            System.out.println("Nhập giá trị của giới hạn giá phía trên");
            upperPriceLimit = Float.parseFloat(scanner.nextLine());
            if (upperPriceLimit <= lowerPriceLimit) {
                System.out.println("Giới hạn giá phía trên phải lớn hơn giới hạn giá phía dưới");
            }else{
                break;
            }
        }
        System.out.println("Danh sách sản phẩm trong khoảng giá đó là :");
        boolean isExist = true;
        for (int i = 0; i < indexProduct; i++) {
            if (arrProducts[i].getPrice() >= lowerPriceLimit && arrProducts[i].getPrice() <= upperPriceLimit){
                System.out.println(arrProducts[i].getProductName()+" Giá : "+arrProducts[i].getPrice());
                isExist = false;
            }
        }
        if (isExist){
            System.out.println("Không có sản phẩm nào trong khoảng giá đó");
        }
    }

    public int getIndexProductById(String productId) {
        for (int i = 0; i < indexProduct; i++) {
            if (Objects.equals(arrProducts[i].getProductId(), productId)) {
                return i;
            }
        }
        return -1;
    }
}

