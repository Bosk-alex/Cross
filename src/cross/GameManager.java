package cross;

import java.util.Random;

class GameManager {

    public static final String AI_WIN_STEP = "AI's win step ";
    public static final String AI_BLOCK_STEP = "AI's block step ";
    Random rand = new Random();

    void randomStep() {
        int x;
        int y;
        do{
            x = rand.nextInt(Map.fieldSizeX);
            y = rand.nextInt(Map.fieldSizeY);
        } while(!isCellValid(y,x));
        Map.field[y][x] = 2;
        System.out.println("AI's random step " + (x + 1) + ", " + (y + 1));
    }

    // 14. Ход ПК
    void aiStep() {
        int line = 0;
        int col = 0;
        boolean bool = true;

        // 1. Check AI's win-step.
        // 1.1. horisontal
        for (int i = 0; i < Map.field.length; i++) {
            if (!bool) break;
            for (int j = 0; j <= Map.field[0].length - Map.winLength; j++) {
                if (!bool) break;
                int compAI = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i][j + k] == 0) {
                        line = i;
                        col = j + k;
                        compEm = 1;
                    } else if (Map.field[i][j + k] == 2){
                        compAI += 1;
                    }

                }
                if (compEm == 1 && compAI == Map.winLength - 1) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_WIN_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }
        // 1.2. vertical
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            if (!bool) break;
            for (int j = 0; j < Map.field[0].length; j++) {
                if (!bool) break;
                int compAI = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j] == 0) {
                        line = i + k;
                        col = j;
                        compEm = 1;
                    } else if (Map.field[i + k][j] == 2) {
                        compAI += 1;
                    }
                }
                if (compEm == 1 && compAI == Map.winLength - 1) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_WIN_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }
        // 1.3. first diagonal
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            if (!bool) break;
            for (int j = 0; j <= Map.field[0].length - Map.winLength; j++) {
                if (!bool) break;
                int compAI = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j + k] == 0) {
                        line = i + k;
                        col = j + k;
                        compEm = 1;
                    } else if (Map.field[i + k][j + k] == 2) {
                        compAI += 1;
                    }
                }
                if (compEm == 1 && compAI == Map.winLength - 1) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_WIN_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }
        // 1.4. second diagonal
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            if (!bool) break;
            for (int j = Map.winLength - 1; j < Map.field[0].length; j++) {
                if (!bool) break;
                int compAI = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j - k] == 0) {
                        line = i + k;
                        col = j - k;
                        compEm = 1;
                    } else if (Map.field[i + k][j - k] == 2) {
                        compAI += 1;
                    }
                }
                if (compEm == 1 && compAI == Map.winLength - 1) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_WIN_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }
        // 2. Check AI's block step.
        // 2.1. horisontal
        for (int i = 0; i < Map.field.length; i++) {
            if (!bool) break;
            for (int j = 0; j <= Map.field[0].length - Map.winLength; j++) {
                if (!bool) break;
                int compPL = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i][j + k] == 0) {
                        line = i;
                        col = j + k;
                        compEm = 1;
                    } else if (Map.field[i][j + k] == 1) {
                        compPL += 1;
                    }
                }
                if (compEm == 1 && compPL == Map.winLength - 1) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_WIN_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }
        // 2.2. vertical
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            if (!bool) break;
            for (int j = 0; j < Map.field[0].length; j++) {
                if (!bool) break;
                int compPL = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j] == 0) {
                        line = i + k;
                        col = j;
                        compEm = 1;
                    } else if (Map.field[i + k][j] == 1) {
                        compPL += 1;
                    }
                }
                if (compEm == 1 && compPL == Map.winLength - 1) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_BLOCK_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }
        // 2.3. first diagonal
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            if (!bool) break;
            for (int j = 0; j <= Map.field[0].length - Map.winLength; j++) {
                if (!bool) break;
                int compPL = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j + k] == 0) {
                        line = i + k;
                        col = j + k;
                        compEm = 1;
                    } else if (Map.field[i + k][j + k] == 1) {
                        compPL += 1;
                    }
                }
                if (compEm == 1 && compPL == Map.winLength - 1) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_BLOCK_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }
        // 2.4. second diagonal
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            if (!bool) break;
            for (int j = Map.winLength - 1; j < Map.field[0].length; j++) {
                if (!bool) break;
                int compPL = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j - k] == 0) {
                        line = i + k;
                        col = j - k;
                        compEm = 1;
                    } else if (Map.field[i + k][j - k] == 1) {
                        compPL += 1;
                    }
                }
                if (compEm == 1 && compPL == Map.winLength - 1) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_BLOCK_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }

        // 3. Check AI's second block step.
        // 3.1. horisontal
        for (int i = 0; i < Map.field.length; i++) {
            if (!bool) break;
            for (int j = 0; j <= Map.field[0].length - Map.winLength; j++) {
                if (!bool) break;
                int compPL = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i][j + k] == 0) {
                        line = i;
                        col = j + k;
                        compEm = 1;
                    } else if (Map.field[i][j + k] == 1) {
                        compPL += 1;
                    }
                }
                if (compEm == 1 && compPL == Map.winLength - 2) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_BLOCK_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }
        // 3.2. vertical
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            if (!bool) break;
            for (int j = 0; j < Map.field[0].length; j++) {
                if (!bool) break;
                int compPL = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j] == 0) {
                        line = i + k;
                        col = j;
                        compEm = 1;
                    } else if (Map.field[i + k][j] == 1) {
                        compPL += 1;
                    }
                }
                if (compEm == 1 && compPL == Map.winLength - 2) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_BLOCK_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }
        // 3.3. first diagonal
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            if (!bool) break;
            for (int j = 0; j <= Map.field[0].length - Map.winLength; j++) {
                if (!bool) break;
                int compPL = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j + k] == 0) {
                        line = i + k;
                        col = j + k;
                        compEm = 1;
                    } else if (Map.field[i + k][j + k] == 1) {
                        compPL += 1;
                    }
                }
                if (compEm == 1 && compPL == Map.winLength - 2) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_BLOCK_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }
        // 3.4. second diagonal
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            if (!bool) break;
            for (int j = Map.winLength - 1; j < Map.field[0].length; j++) {
                if (!bool) break;
                int compPL = 0;
                int compEm = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j - k] == 0) {
                        line = i + k;
                        col = j - k;
                        compEm = 1;
                    } else if (Map.field[i + k][j - k] == 1) {
                        compPL += 1;
                    }
                }
                if (compEm == 1 && compPL == Map.winLength - 2) {
                    Map.field[line][col] = 2;
                    bool = false;
                    System.out.println(AI_BLOCK_STEP + (col + 1) + ", " + (line + 1));
                    break;
                }
            }
        }

        if (bool) randomStep();
    }

    boolean checkWin(int sym) {
        // 1. horisontal
        for (int i = 0; i < Map.field.length; i++) {
            for (int j = 0; j <= Map.field[0].length - Map.winLength; j++) {
                int comp = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i][j + k] == sym) {
                        comp += 1;
                    } else break;
                    if (comp == Map.winLength) {
                        return true;
                    }
                }
            }
        }
        // 2. vertical
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            for (int j = 0; j < Map.field[0].length; j++) {
                int comp = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j] == sym) {
                        comp += 1;
                    } else break;
                    if (comp == Map.winLength) {
                        return true;
                    }
                }
            }
        }
        // 3. first diagonal
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            for (int j = 0; j <= Map.field[0].length - Map.winLength; j++) {
                int comp = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j + k] == sym) {
                        comp += 1;
                    } else break;
                    if (comp == Map.winLength) {
                        return true;
                    }
                }
            }
        }
        // 4. second diagonal
        for (int i = 0; i <= Map.field.length - Map.winLength; i++) {
            for (int j = Map.winLength - 1; j < Map.field[0].length; j++) {
                int comp = 0;
                for (int k = 0; k < Map.winLength; k++) {
                    if (Map.field[i + k][j - k] == sym) {
                        comp += 1;
                    } else break;
                    if (comp == Map.winLength) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 16. Проверка полное ли поле? возможно ли ходить?
    boolean isFieldFull() {
        for (int i = 0; i < Map.fieldSizeY; i++) {
            for(int j = 0; j < Map.fieldSizeX; j++) {
                if(Map.field[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 10. Проверяем возможен ли ход
    boolean isCellValid(int y, int x) {
        // если вываливаемся за пределы возвращаем false
        if(x < 0 || y < 0 || x > Map.fieldSizeX - 1 || y > Map.fieldSizeY - 1) {
            return false;
        }
        // если не путое поле тоже false
        return (Map.field[y][x] == 0);
    }

}
