# 📚 Library Management System  

A simple and efficient **Library Management System** built in **Java** using **OOP principles**.  
This system allows librarians to manage books and users, while users can borrow and return books seamlessly.  

## ✨ Features  
- Add books to the library  
- Register users  
- Borrow and return books  
- Display available books  
- Track borrowed books for each user  

## 🛠 Technologies Used  
- **Java**  
- **Collections Framework (ArrayList)**  

## 🏛️ Project Structure  

### 📖 Book  
Represents a book in the library.  
- Stores book details like title, author, ISBN, and availability status.  
- **Methods:** `borrowBook()`, `returnBook()`, `isAvailable()`, etc.  

### 👤 User  
Represents a library user.  
- Stores user details and borrowed books.  
- **Methods:** `borrowBook(Book book)`, `returnBook(Book book)`, `displayBorrowedBooks()`, etc.  

### 🏢 Librarian  
Manages books in the library.  
- **Methods:** `addBook()`, `displayBooks()`.  

### 📚 LibrarySystem  
Handles all operations related to books and users.  
- **Methods:** `addBook()`, `registerUser()`, `borrowBook()`, `returnBook()`, `displayAvailableBooks()`.  

### 🚀 Main  
The entry point of the program.  
- Demonstrates the system by adding books, registering users, borrowing and returning books.  

## 🖥️ How to Run  
1. **Clone this repository:**  
   ```sh
   git clone https://github.com/your-username/library-management-system.git
