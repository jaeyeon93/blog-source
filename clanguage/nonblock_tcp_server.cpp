#include "common.h"
#define SERVERPORT 9000
#define BUFSIZE 512

int main(int argc, char *argv[]) {
    int retval;

    // create socket
    SOCKET listen_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (listen_socket == INVALID_SOCKET) {
        err_quit("listen socket generate fail");
    } else {
        printf("listen socket generate success");
    }

    // non block socket change
    int flags = fcntl(listen_socket, F_GETFL);
    flags |= O_NONBLOCK;
    fcntl(listen_socket, F_SETFL, flags);

    // bind()
    struct sockaddr_in serveraddr;
    memset(&serveraddr, 0, sizeof(serveraddr));
    serveraddr.sin_family = AF_INET;
    serveraddr.sin_addr.s_addr = htonl(INADDR_ANY);
    serveraddr.sin_port = htons(SERVERPORT);

    retval = bind(listen_socket, (struct sockaddr *)&serveraddr, sizeof(serveraddr));
    if (retval == SOCKET_ERROR) {
        err_quit("socket bind fail");
    } else {
        printf("socket bind success\n");
    }

    // listen()
    retval = listen(listen_socket, SOMAXCONN);
    if (retval == SOCKET_ERROR)
    {
        err_quit("listen()");
    }
    else
    {
        printf("listen success");
    };

    SOCKET client_sock;
    struct sockaddr_in clientaddr;
    socklen_t addrlen;
    char buf[BUFSIZE + 1];

    while (1) {
        // accept
        addrlen = sizeof(clientaddr);
        client_sock = accept(listen_socket, (struct sockaddr *)&clientaddr, &addrlen);
        if (client_sock == INVALID_SOCKET)
        {
            err_display("accept()");
            break;
        }

        // 넌블로킹 소켓으로 전환
        int flags = fcntl(client_sock, F_GETFL);
        flags |= O_NONBLOCK;
        fcntl(client_sock, F_SETFL, flags);


        // client 정보 출력
        char addr[INET_ADDRSTRLEN];
        inet_ntop(AF_INET, &clientaddr.sin_addr, addr, sizeof(addr));
        printf("\n[TCP 서버] 클라이언트 접속: IP 주소=%s, 포트 번호=%d\n",
               addr, ntohs(clientaddr.sin_port));

        // 클라이언트와 데이터 통신
        while (1)
        {
            // 데이터 받기
            retval = recv(client_sock, buf, BUFSIZE, 0);
            if (retval == SOCKET_ERROR)
            {
                err_display("recv()");
                break;
            }
            else if (retval == 0)
                break;

            // 받은 데이터 출력
            buf[retval] = '\0';
            printf("[TCP/%s:%d] %s\n", addr, ntohs(clientaddr.sin_port), buf);

            // 데이터 보내기
            retval = send(client_sock, buf, retval, 0);
            if (retval == SOCKET_ERROR)
            {
                err_display("send()");
                break;
            }

            printf("[TCP 서버] 클라이언트 종료: IP 주소=%s, 포트 번호=%d\n",
                   addr, ntohs(clientaddr.sin_port));
        }
    }

    close(listen_socket);
    return 0;
}
