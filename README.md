
# 🏰 Medieval Kingdom Management Game

A text-based strategy game built with **Java** that demonstrates the core principles of Object-Oriented Programming. Manage your kingdom, balance your economy, and keep your citizens happy across different types of cities!

## 🕹️ Game Overview
In this game, you are the ruler of a kingdom for **10 years (turns)**. Your goal is to keep the kingdom thriving by managing three distinct cities. If your resources (Gold/Food) run out or your people become too unhappy, your reign ends!

### 🌆 City Types
- **Trading City (Ticaret Şehri):** High gold production, low food.
- **Agriculture City (Tarım Şehri):** High food production, low gold.
- **Industrial City (Sanayi Şehri):** Balanced production of both resources.

## 🚀 Core Features & OOP Implementation
- **Inheritance:** All city types extend from a base `Sehir` abstract class.
- **Polymorphism:** Each city overrides `turSonuUretimHesapla()` to calculate unique resource yields.
- **Dynamic Events:** Random "Raids" (Baskınlar) occur based on the city's security and army levels.
- **Management Systems:**
  - **Investment:** Use gold to level up cities.
  - **Taxation:** Adjust tax rates between 0.0 and 1.0 (Watch out! High taxes lower happiness).
  - **Resource Tracking:** Global tracking of total Gold and Food.

## 📋 Project Structure
- `Krallik.java`: The main engine that manages turns and game logic.
- `Sehir.java`: Abstract base class for all city implementations.
- `TicaretSehri.java`, `TarimSehri.java`, `SanayiSehri.java`: Specialized city classes.
- `Test.java`: Entry point of the application.
