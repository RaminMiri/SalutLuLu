/* 
 * File:   main.c
 * Author: raminmiri
 *
 * Created on October 16, 2015, 12:32 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h> 
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <math.h>
#include <stdarg.h>


#define ARG_DELIMS "\n"
#define BLOCK_SIZE 8
#define DISKSIZE 500000

typedef unsigned char byte;

typedef struct _inode {
    int _block;
    unsigned int size;
    char type;
    byte data_blocks[8];
} inode;

typedef struct _dir_entry {
    char name[40];
    int inode_block;
} dir_entry;

typedef struct _dir {
    int _block;
    unsigned int file_count;
    dir_entry files[8];
} dir;


void initialize_new_disk();
void initialize_old_disk();

void cmd_lire(char *file_name);
void cmd_ecrire(char *cmd);
void cmd_suppression(char *cmd);
void cmd_mkdir(char *cmd);
void cmd_suppdir(char *cmd);
void fermer();

int initialize();
char **tokenize_str(char *str, char *delims, unsigned int max_tokens);
char *lire_line(FILE *in);

unsigned char disk[256][8];
int g_free_block_count;
int g_cwd_block;

int main(int argc, char *argv[]) {
    int r;
    FILE *fp = stdin;

    r = initialize();
    if (r < 0) {
        fprintf(stderr, "error opening filesystem.txt\n");
        return 1;
    } else if (r == 0)
        initialize_new_disk();
    else
        initialize_old_disk();

    if (argc == 2) {
        fp = fopen(argv[1], "r");
        if (!fp) {
            perror("peut pas le script file");
            return 1;
        }
    }

    g_cwd_block = 1;
    while (1) {
        char *cmd, **argv;

        cmd = lire_line(fp);
        if (!cmd)
            continue;
        argv = tokenize_str(cmd, ARG_DELIMS, 2);

        if (argv != NULL) {
            if (argv[0] != NULL) {
                // Il faut implementer les commandes si-desous.. 
                if (!strcmp("lire_fichier", argv[0]))
                    cmd_lire(argv[1]);
                else if (!strcmp("ecrire", argv[0]))
                    cmd_ecrire(argv[1]);
                else if (!strcmp("suppression_fichier", argv[0]))
                    cmd_suppression(argv[1]);
                else if (!strcmp("creation_repertoire", argv[0]))
                    cmd_mkdir(argv[1]);
                else if (!strcmp("suppression_repertoire", argv[0]))
                    cmd_suppdir(argv[1]);
                else if (!strcmp("fin", argv[0])) {
                    fermer();
                    return 0;
                } else {
                    fprintf(stderr, "unknown command '%s'\n", argv[0]);
                }
            }
            free(argv);
        }
    }
    return 0;
}
