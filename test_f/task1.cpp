#include <iostream>
#include <vector>
#include <random>
#include <algorithm>
#include <fstream>

typedef std::vector<std::vector<long>> mtrx;

const long MOD = 1e9 + 7;
const int sz = 2;

mtrx mult(const mtrx& l, const mtrx& r){
    mtrx res(l.size(), std::vector<long>(r[0].size(), 0));
    for(int i = 0; i < res.size(); ++i){
        for(int j = 0; j < res[0].size(); ++j){
            long& v = res[i][j];
            for(int k = 0; k < r.size(); ++k){
                v = (v + (l[i][k] * r[k][j]) % MOD) % MOD;
                if(v <= 0){
                    v += MOD;
                }
            }
        }
    }
    return res;
}

void calc(mtrx& m, int n){
    if(n == 1){
        return;
    }else if(n == 2){
        m = mult(m, m);    
    }else if(n % 2 == 0){
        calc(m, n/2);
        m = mult(m, m);
    }else{
        mtrx cp(m);
        calc(m, (n - 1)/2);
        m = mult(m, m);
        m = mult(m, cp);
    }
}

int main(){
    int n;
    std::cin >> n;
    if(n == 1){
        std::cout << 0 << '\n';
        return 0;
    }
    mtrx m = {{2, 3}, {1, 0}};
    calc(m, n);
    mtrx l(2, std::vector<long>(1, 0));
    l[0][0] = 0;
    l[1][0] = 1;
    mtrx res = mult(m, l);
    long v = (res[1][0]) % MOD;
    if(v < 0){
        v += MOD;
    }
    std::cout << v << '\n';   
}