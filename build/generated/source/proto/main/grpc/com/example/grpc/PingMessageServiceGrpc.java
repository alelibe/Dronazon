package com.example.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: PingMessage.proto")
public final class PingMessageServiceGrpc {

  private PingMessageServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.PingMessageService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.PingMessage.PingHello,
      com.example.grpc.PingMessage.Ack> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ping",
      requestType = com.example.grpc.PingMessage.PingHello.class,
      responseType = com.example.grpc.PingMessage.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.PingMessage.PingHello,
      com.example.grpc.PingMessage.Ack> getPingMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.PingMessage.PingHello, com.example.grpc.PingMessage.Ack> getPingMethod;
    if ((getPingMethod = PingMessageServiceGrpc.getPingMethod) == null) {
      synchronized (PingMessageServiceGrpc.class) {
        if ((getPingMethod = PingMessageServiceGrpc.getPingMethod) == null) {
          PingMessageServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.PingMessage.PingHello, com.example.grpc.PingMessage.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.PingMessage.PingHello.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.PingMessage.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new PingMessageServiceMethodDescriptorSupplier("ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PingMessageServiceStub newStub(io.grpc.Channel channel) {
    return new PingMessageServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PingMessageServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PingMessageServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PingMessageServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PingMessageServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PingMessageServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void ping(com.example.grpc.PingMessage.PingHello request,
        io.grpc.stub.StreamObserver<com.example.grpc.PingMessage.Ack> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.PingMessage.PingHello,
                com.example.grpc.PingMessage.Ack>(
                  this, METHODID_PING)))
          .build();
    }
  }

  /**
   */
  public static final class PingMessageServiceStub extends io.grpc.stub.AbstractStub<PingMessageServiceStub> {
    private PingMessageServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PingMessageServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PingMessageServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PingMessageServiceStub(channel, callOptions);
    }

    /**
     */
    public void ping(com.example.grpc.PingMessage.PingHello request,
        io.grpc.stub.StreamObserver<com.example.grpc.PingMessage.Ack> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PingMessageServiceBlockingStub extends io.grpc.stub.AbstractStub<PingMessageServiceBlockingStub> {
    private PingMessageServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PingMessageServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PingMessageServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PingMessageServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.PingMessage.Ack ping(com.example.grpc.PingMessage.PingHello request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PingMessageServiceFutureStub extends io.grpc.stub.AbstractStub<PingMessageServiceFutureStub> {
    private PingMessageServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PingMessageServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PingMessageServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PingMessageServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.PingMessage.Ack> ping(
        com.example.grpc.PingMessage.PingHello request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PingMessageServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PingMessageServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((com.example.grpc.PingMessage.PingHello) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.PingMessage.Ack>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PingMessageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PingMessageServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.PingMessage.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PingMessageService");
    }
  }

  private static final class PingMessageServiceFileDescriptorSupplier
      extends PingMessageServiceBaseDescriptorSupplier {
    PingMessageServiceFileDescriptorSupplier() {}
  }

  private static final class PingMessageServiceMethodDescriptorSupplier
      extends PingMessageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PingMessageServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PingMessageServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PingMessageServiceFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
